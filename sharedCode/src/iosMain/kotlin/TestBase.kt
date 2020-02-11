package com.laskowski.kuiks

import platform.Foundation.NSInvocation

actual open class TestBase

val testInvocations: List<NSInvocation>
    get() = emptyList()