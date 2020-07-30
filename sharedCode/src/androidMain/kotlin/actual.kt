package dev.michallaskowski.kuiks

import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.StringContains.containsString


actual val platform: Platform = Platform.Android

class ElementWrapper(val matcher: Matcher<View>): AppElement {
    override fun tap() {
        Espresso.onView(matcher).perform(ViewActions.click())
    }

    override fun elementWithTestId(testId: String): AppElement {

        val interaction = Matchers.allOf(
            ViewMatchers.withContentDescription(testId),
            ViewMatchers.isDescendantOfA(matcher)
        )
        return ElementWrapper(interaction)
    }

    override fun table(withId: String): AppElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cell(withId: String): AppElement {
        val cellMatcher = ViewMatchers.withContentDescription(withId)
        Espresso.onView(matcher).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(cellMatcher))
        return ElementWrapper(cellMatcher)
    }

    override fun waitForExistence(timeout: Double) {
        Espresso.onView(matcher).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    override fun hasText(text: String, timeout: Double) {
        ViewAssertionIdler.waitFor(
            matcher,
            ViewAssertions.matches(ViewMatchers.withText(containsString(text))),
            timeout
        )
    }

    override fun getText(timeout: Double): String {
        var text = ""
        onView(matcher).check { hasTextView, noTextView ->
            if (noTextView != null) {
                throw ClassCastException("Non TextView or EditText matchers do not have text")
            }
            if (hasTextView == null) {
                throw Exception("View is null")
            }
            text = if (hasTextView is TextView) {
                hasTextView.text.toString()
            } else {
                (hasTextView as EditText).text.toString()
            }
        }
        return text
    }

    override val debugDescription: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}

actual class ApplicationWrapper actual constructor(private val identifier: String): Application {
    override fun launch(arguments: Map<String, String>) {
        // based on https://github.com/appium/appium-espresso-driver/blob/05ccf5a7fa440bfee96400b52672287458676dfa/espresso-server/app/src/androidTest/java/io/appium/espressoserver/lib/helpers/ActivityHelper.kt
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val appPackage = instrumentation.targetContext.packageName
        val activityName = "$appPackage.$identifier"
        val intent = Intent(Intent.ACTION_MAIN)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.setClassName(instrumentation.targetContext, activityName)

        arguments.forEach {
            intent.putExtra(it.key, it.value)
        }

        instrumentation.startActivitySync(intent)
    }

    override fun tap() {
        TODO("not implemented")
    }

    override fun elementWithTestId(testId: String): AppElement {
        return ElementWrapper(ViewMatchers.withContentDescription(testId))
    }

    override fun table(withId: String): AppElement {
        return elementWithTestId(withId)
    }

    override fun cell(withId: String): AppElement {
        TODO("not implemented")
    }

    override fun waitForExistence(timeout: Double) {
        TODO("not implemented")
    }

    override fun hasText(text: String, timeout: Double) {
        TODO("not implemented")
    }

    override fun getText(timeout: Double): String {
        TODO("not implemented")
    }

    override val debugDescription: String
        get() = identifier
}
