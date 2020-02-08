package dev.michallaskowski.kuiks.sample.sharedTests

import com.laskowski.kuiks.ApplicationWrapper
import com.laskowski.kuiks.Platform
import com.laskowski.kuiks.platform
import kotlin.test.Test

open class TestExample {

    val identifier: String
        get() = if (platform == Platform.iOS) "" else "MainActivity"

    @Test
    fun testOne() {
        val app = ApplicationWrapper(identifier)
        app.launch()
    }
}