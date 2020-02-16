package com.laskowski.kuiks

import android.content.Intent
import android.view.View
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun waitForExistence(timeout: Double) {
        Espresso.onView(matcher).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    override val debugDescription: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}

actual class ApplicationWrapper actual constructor(private val identifier: String): Application {
    override fun launch() {
        // based on https://github.com/appium/appium-espresso-driver/blob/05ccf5a7fa440bfee96400b52672287458676dfa/espresso-server/app/src/androidTest/java/io/appium/espressoserver/lib/helpers/ActivityHelper.kt
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val appPackage = instrumentation.targetContext.packageName
        val activityName = "$appPackage.$identifier"
        val intent = Intent(Intent.ACTION_MAIN)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.setClassName(instrumentation.targetContext, activityName)
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

    override val debugDescription: String
        get() = identifier
}
