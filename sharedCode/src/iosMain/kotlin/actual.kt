package dev.michallaskowski.kuiks

import platform.XCTest.*
import platform.Foundation.*

actual val platform: Platform = Platform.iOS

class UIElementWrapper(val query: XCUIElementQuery): AppElement {
    override fun tap() {
        query.element.tap()
    }

    override fun elementWithTestId(testId: String): AppElement {
        return elementWith(testId, XCUIElementTypeAny)
    }

    override fun table(withId: String): AppElement {
        return elementWith(withId, XCUIElementTypeTable)
    }

    override fun cell(withId: String): AppElement {
        val query = query.cells.matchingType(XCUIElementTypeCell, withId)
        return UIElementWrapper(query)
    }

    override fun waitForExistence(timeout: Double) {
        query.element.waitForExistenceWithTimeout(timeout)
    }

    override fun hasText(text: String, timeout: Double) {
        val predicate = NSPredicate.predicateWithFormat("label CONTAINS[c] '${text}'")
        query.matchingIdentifier("label").matchingPredicate(predicate).element.waitForExistenceWithTimeout(1.0)
    }

    override fun getText(timeout: Double): String {
        return query.matchingIdentifier("label").element.label
    }

    private fun elementWith(testId: String, type: XCUIElementType): AppElement {
        val query = query.descendantsMatchingType(type).matchingIdentifier(testId)
        return UIElementWrapper(query)
    }

    override val debugDescription: String
        get() = query.debugDescription()
}

actual class ApplicationWrapper actual constructor(identifier: String) : Application {

    private val app: XCUIApplication = XCUIApplication()

    override fun launch(arguments: Map<String, String>) {
        val launchArguments = arguments.flatMap {
            listOf("-${it.key}", "${it.value}")
        }
        app.launchArguments = launchArguments
        app.launch()
    }

    override fun tap() {
        app.tap()
    }

    override fun elementWithTestId(testId: String): AppElement {
        return elementWith(testId, XCUIElementTypeAny)
    }

    override fun table(withId: String): AppElement {
        return elementWith(withId, XCUIElementTypeTable)
    }

    override fun cell(withId: String): AppElement {
        return elementWith(withId, XCUIElementTypeCell)
    }

    override fun waitForExistence(timeout: Double) {
        app.waitForExistenceWithTimeout(timeout)
    }

    override fun hasText(text: String, timeout: Double) {
        val predicate = NSPredicate.predicateWithFormat("label CONTAINS[c] '${text}'")
        app.staticTexts.elementMatchingPredicate(predicate).waitForExistenceWithTimeout(1.0)
    }

    override fun getText(timeout: Double): String {
        TODO()
    }

    private fun elementWith(testId: String, type: XCUIElementType): AppElement {
        val query = app.descendantsMatchingType(type).matchingIdentifier(testId)
        return UIElementWrapper(query)
    }

    override val debugDescription: String
        get() = app.debugDescription()
}
