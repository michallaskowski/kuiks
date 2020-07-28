package dev.michallaskowski.kuiks.sample.sharedTests

import dev.michallaskowski.kuiks.Platform
import dev.michallaskowski.kuiks.platform

object AppSetup {
    val identifier: String
        get() = if (platform == Platform.iOS) "dev.michallaskowski.kuiks.SampleiOS" else "MainActivity"
}