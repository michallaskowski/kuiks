//
//  AutoDiscoveredTests.swift
//  SampleiOS
//
//  Created by Michał Laskowski on 10/02/2020.
//  Copyright © 2016 Michał Laskowski. All rights reserved.
//

import Foundation

class TestBase<TestClass: NSObject>: TestBaseForSelector {

    override class func testCase(for selector: Selector) -> XCTestCase {
        let testObject = TestClass.init()
        addInstanceMethod(object: testObject, selector: selector)
        return SampleiOSUITests(selector: selector)
    }

    // based on https://github.com/Quick/Quick/blob/4a4b6c7dd5aac29e0ea8263d1829de006227f59e/Sources/Quick/QuickSpec.swift
    override class var defaultTestSuite: XCTestSuite {
        registerTestSelectors()
        return super.defaultTestSuite
    }

    override class func registerTestMethods() -> [String] {
        return registerTestSelectors().map {
            NSStringFromSelector($0)
        }
    }

    override class func testClass() -> AnyClass {
        return TestClass.self
    }

    @discardableResult private class func registerTestSelectors() -> [Selector] {
        let clazz = TestClass.self

        var methodCount: UInt32 = 0
        let methodList = class_copyMethodList(clazz, &methodCount)
        let testObject = TestClass.init()

        var selectors: [Selector] = []
        if let methodList = methodList, methodCount > 0 {
            enumerateCArray(array: methodList, count: methodCount) { i, m in
                let selector = method_getName(m)
                let selectorName = NSStringFromSelector(selector)

                if selectorName.hasPrefix("test") {
                    addInstanceMethod(object: testObject, selector: selector)
                    selectors.append(selector)
                }
            }

            free(methodList)
        }
        return selectors
    }

    private static func addInstanceMethod(object: NSObject, selector: Selector) {
        let block: @convention(block) () -> Void = {
            object.perform(selector, on: Thread.main, with: nil, waitUntilDone: true)
        }
        let implementation = imp_implementationWithBlock(block as Any)
        class_addMethod(self, selector, implementation, "v@:")
    }
}

func enumerateCArray<T>(array: UnsafePointer<T>, count: UInt32, f: (UInt32, T) -> Void) {
    var ptr = array
    for i in 0..<count {
        f(i, ptr.pointee)
        ptr = ptr.successor()
    }
}
