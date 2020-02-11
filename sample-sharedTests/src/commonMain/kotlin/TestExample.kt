package dev.michallaskowski.kuiks.sample.sharedTests

import com.laskowski.kuiks.ApplicationWrapper
import com.laskowski.kuiks.Platform
import com.laskowski.kuiks.TestBase
import com.laskowski.kuiks.platform
import kotlin.test.Test

class TestExample: TestBase() {

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
}