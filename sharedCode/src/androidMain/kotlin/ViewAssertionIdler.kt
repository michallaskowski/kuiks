package dev.michallaskowski.kuiks

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewFinder
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher
import java.util.concurrent.TimeUnit

/**
 * Based on https://stackoverflow.com/questions/50628219/is-it-possible-to-use-espressos-idlingresource-to-wait-until-a-certain-view-app
 * and https://medium.com/azimolabs/wait-for-it-idlingresource-and-conditionwatcher-602055f32356
 */
class ViewAssertionIdler private constructor(
    private val viewMatcher: Matcher<View>,
    private val viewAssertion: ViewAssertion
) : IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null
    private var isIdle = false

    override fun getName(): String = javaClass.simpleName

    override fun isIdleNow(): Boolean {
        if (isIdle) return true
        isIdle = isViewAssertionMetNow()
        if (isIdle) {
            callback!!.onTransitionToIdle()
        }

        return isIdle
    }

    private fun isViewAssertionMetNow(): Boolean {
        val testView = getView(viewMatcher) ?: return false

        return try {
            viewAssertion.check(testView, null)
            true
        } catch (e: AssertionFailedError) {
            false
        }
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    companion object {

        private fun getView(viewMatcher: Matcher<View>): View? {
            return try {
                val viewInteraction = onView(viewMatcher)
                val finderField = viewInteraction.javaClass.getDeclaredField("viewFinder")
                finderField.isAccessible = true
                val finder = finderField.get(viewInteraction) as ViewFinder
                finder.view
            } catch (e: Exception) {
                null
            }
        }

        fun waitFor(
            viewMatcher: Matcher<View>,
            viewAssertion: ViewAssertion,
            timeout: Double
        ) {
            IdlingPolicies.setMasterPolicyTimeout(timeout.times(1000).toLong(), TimeUnit.MILLISECONDS)
            IdlingPolicies.setIdlingResourceTimeout(timeout.times(1000).toLong(), TimeUnit.MILLISECONDS)
            val idler = ViewAssertionIdler(viewMatcher, viewAssertion)
            val idlingRegistry = IdlingRegistry.getInstance()

            try {
                idlingRegistry.register(idler)
                onView(viewMatcher).check(viewAssertion)
            } finally {
                idlingRegistry.unregister(idler)
            }
        }
    }
}