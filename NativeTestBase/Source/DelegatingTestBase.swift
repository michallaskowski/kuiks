//
//  AutoDiscoveredTests.swift
//  SampleiOS
//
//  Created by Michał Laskowski on 10/02/2020.
//  Copyright © 2016 Michał Laskowski. All rights reserved.
//

import Foundation
import NativeTestObjCBase
import XCTest

open class DelegatingTestBase: TestBaseForSelector {

    // needs to be overriden to return class implementing test methods
    open class func testClass() -> AnyClass {
        return NSObject.self
    }

    // only called when running one test from Test navigator or sidebar
    public override class func registerTest(for selector: Selector) {
        // casting to NSObject.Type needed because:
        // https://stackoverflow.com/questions/55831682/swift-thinks-im-subclassing-nsset-wrongly-but-im-not-subclassing-it-at-all
        let testObject = (testClass() as! NSObject.Type).init()
        addInstanceMethod(object: testObject, selector: selector)
    }

    // inspired by https://github.com/Quick/Quick/blob/4a4b6c7dd5aac29e0ea8263d1829de006227f59e/Sources/Quick/QuickSpec.swift
    public override class var defaultTestSuite: XCTestSuite {
        registerTestSelectors()
        return super.defaultTestSuite
    }

    private class func registerTestSelectors() {
        var methodCount: UInt32 = 0
        let methodList = class_copyMethodList(testClass(), &methodCount)
        defer { free(methodList) }
        guard methodCount > 0 else {
            return
        }

        let testInstance = (testClass() as! NSObject.Type).init()

        if let methodList = methodList, methodCount > 0 {
            enumerateCArray(array: methodList, count: methodCount) { i, m in
                let selector = method_getName(m)
                let selectorName = NSStringFromSelector(selector)

                if selectorName.hasPrefix("test") {
                    addInstanceMethod(object: testInstance, selector: selector)
                }
            }
        }
    }

    private static func addInstanceMethod(object: NSObject, selector: Selector) {
        let block: @convention(block) () -> Void = {
            // setUp
            if object.responds(to: #selector(setUp)) {
                object.perform(#selector(setUp))
            }

            // run test
            object.perform(selector, on: Thread.main, with: nil, waitUntilDone: true)

            // tear down
            if object.responds(to: #selector(tearDown)) {
                object.perform(#selector(tearDown))
            }
        }
        let implementation = imp_implementationWithBlock(block as Any)
        class_addMethod(self, selector, implementation, "v@:")
    }
}

private func enumerateCArray<T>(array: UnsafePointer<T>, count: UInt32, f: (UInt32, T) -> Void) {
    var ptr = array
    for i in 0..<count {
        f(i, ptr.pointee)
        ptr = ptr.successor()
    }
}
