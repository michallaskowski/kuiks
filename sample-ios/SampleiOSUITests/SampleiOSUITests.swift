//  Copyright © 2020 Michal Laskowski. All rights reserved.

import XCTest
import sharedTests

class SampleiOSUITests: TestBase<TestExample> {
    override func setUp() {
        continueAfterFailure = false
    }
//
//    override class func testClass() -> AnyClass! {
//        return TestExample.self
//    }

    func testDupa() {
        print("Dupa")
    }
}
