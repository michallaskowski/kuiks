package dev.michallaskowski.kuiks.sample.sharedTests

import dev.michallaskowski.kuiks.ApplicationWrapper
import kotlin.test.BeforeTest
import kotlin.test.Test

open class TestListBehavior {

    private lateinit var app: ApplicationWrapper

    @BeforeTest
    fun setUp() {
        app = ApplicationWrapper(AppSetup.identifier)
    }

    @Test
    fun testOne() {
        app.launch()
    }

    @Test
    fun testEmpty() {}

    @Test
    fun testListWithoutScrolling() {
        app.launch()

        app.elementWithTestId("show_list").tap()

        val list = app.table("list")
        list.cell("1").tap()

        app.elementWithTestId("show_list").waitForExistence(1.0)
    }

    @Test
    fun testListWithScrolling() {
        app.launch()

        app.elementWithTestId("show_list").tap()

        val list = app.table("list")
        list.cell("99").tap()

        app.elementWithTestId("show_list").waitForExistence(1.0)
    }
}