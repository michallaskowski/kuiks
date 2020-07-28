package dev.michallaskowski.kuiks

import kotlinx.coroutines.CoroutineScope

// based on https://github.com/touchlab/KaMPKit/blob/master/shared/src/commonTest/kotlin/co/touchlab/kampkit/BaseTest.kt
expect abstract class BaseTest() {
    fun <T> runTest(block: suspend CoroutineScope.() -> T)
}