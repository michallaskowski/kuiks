package dev.michallaskowski.kuiks.sample.sharedTests

import com.laskowski.kuiks.ApplicationWrapper
import com.laskowski.kuiks.Platform
import com.laskowski.kuiks.platform
import kotlin.test.Test

open class TestExample {

    val identifier: String
        get() = if (platform == Platform.iOS) "dev.michallaskowski.kuiks.SampleiOS" else "MainActivity"

    @Test
    fun testOne() {
        val app = ApplicationWrapper(identifier)
        app.launch()
    }

    @Test
    fun testEmpty() {

    }

    @Test
    fun testListWithoutScrolling() {
        val app = ApplicationWrapper(identifier)
        app.launch()

        app.elementWithTestId("show_list").tap()
        app.elementWithTestId("1").tap()

        app.elementWithTestId("show_list").waitForExistence(1.0)
    }

    @Test
    fun testListWithScrolling() {
        val app = ApplicationWrapper(identifier)
        app.launch()

        app.elementWithTestId("show_list").tap()
        val list = app.table("list")

        list.cell("99").tap()

        app.elementWithTestId("show_list").waitForExistence(1.0)
    }
}