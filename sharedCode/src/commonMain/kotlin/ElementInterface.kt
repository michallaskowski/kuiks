package dev.michallaskowski.kuiks

interface AppElement {
    fun tap()
    fun elementWithTestId(testId: String) : AppElement
    fun table(withId: String): AppElement
    fun cell(withId: String): AppElement
    fun waitForExistence(timeout: Double = 5.0)
    fun hasText(text: String, timeout: Double = 5.0)
    fun getText(timeout: Double = 5.0): String
    val debugDescription: String
}

interface Application: AppElement {
    fun launch(arguments: Map<String, String> = emptyMap())
}

@Target(AnnotationTarget.FUNCTION)
annotation class iOSTest

expect class ApplicationWrapper(identifier: String): Application

enum class Platform {
    iOS, Android
}

expect val platform: Platform