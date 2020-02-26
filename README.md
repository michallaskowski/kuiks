![](https://github.com/michallaskowski/kuiks/workflows/Run%20tests/badge.svg?branch=master)

# Kuiks

## What is it

Kuiks is a framework for automated UI tests. It uses [Kotlin Multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) to create common interface for [Espresso](https://developer.android.com/training/testing/espresso) and [XCTest's UI](https://developer.apple.com/documentation/xctest/user_interface_tests) invocations.  
You could say it is similar to [Appium](https://appium.io/), as the end goal is the same - write tests for many platforms, once. But, Appium relies on [WebDriver](https://w3c.github.io/webdriver/) protocol to provide run-time invocations on chosen UI testing drivers (as of now, by default Espresso on Android and XCUITest on iOS).

Kuiks, thanks to Kotlin Multiplatform, provides compile time bridge to the same native UI drivers, and it does not use WebDriver protocol, or any other HTTP protocol, proxies or servers in between. Meaning, **you can run your tests directly from your IDE.**

## When it can be useful

Kuiks is still in an early development phase, and can change a lot. The goal is to have the most reliable, and fastest UI tests possible. With current implementation it is possible to test (or try to) anything that Espresso and XCUITest can test, which are Android, iOS, tvOS, and macOS apps. Currently development is focused on iOS and Android apps, as having similar apps for those platforms is most common.

## How do I use it to test my app

Artifacts are not yet published to any Maven repository. This can and will happen, if there is anyone interested. Feel free to open an issue if you need it.

## How to build and launch sample tests

Clone the repo first.
The samples contain simple button and lists. RecyclerView on Android, and UITableViewController on iOS. There are also tests using those UI elements.

### Android

* Open Android Studio.
* Open the project at the root of cloned repository.
* Make sure you have at least one emulator.
* Run tests from `sample-android` project.  
One of: `InheritedTests`, `InstrumentedTests` or `TestsFactoryTests`.

### iOS

As with anything that is used for development for Apple's devices, you need to do it on macOS.

* Make sure you have Xcode installed, under /Applications/Xcode.app, or you have /Applications/Xcode.app symlink pointing to Xcode installation. Some people, including me, have multiple Xcodes installed, and none under default Xcode.app name.  
(xctest.def [cinterop file](https://kotlinlang.org/docs/reference/native/c_interop.html) for XCUITest is a static file, and right now `xcode-select -p` is not used to set Xcode's installation path before invoking compilation. Not sure yet how it would even work for [published libraries](https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#publishing-a-multiplatform-library).)  
Tested with Xcode11.3 symlinked to /Applications/Xcode.app
* Open `sample-ios/SampleiOS.xcodeproj` project.
* Run tests on `SampleiOS` scheme. `SampleiOSUITests` uses a custom `DelegatingTestBase` to run tests from `sharedTests.TextExample` class.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  
Please make sure to update samples and tests as appropriate.

## Roadmap

* Add more native controls and cases into samples, for example date/time picker.
* Add cases where Android and iOS samples have different flow or UI, to make sure differences between platforms can be handled in shared tests, on platform level.
* Smarter after more complicated cases revisit current public API.
* Publish to a Maven repository.
* Gather feedback.
* Find contributors more familiar with Android, Espresso, Kotlin and Gradle.
