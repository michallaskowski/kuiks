package com.laskowski.kuiks

interface AppElement {
    fun tap()
    fun elementWithTestId(testId: String) : AppElement
    fun table(withId: String): AppElement
    fun cell(withId: String): AppElement
    fun waitForExistence(timeout: Double)
    val debugDescription: String
}

interface Application: AppElement {
    fun launch()
}

@Target(AnnotationTarget.FUNCTION)
annotation class iOSTest

expect class ApplicationWrapper: Application {

}