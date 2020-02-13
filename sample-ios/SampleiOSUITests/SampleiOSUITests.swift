//  Copyright © 2020 Michal Laskowski. All rights reserved.

import XCTest
import sharedTests

class SampleiOSUITests: DeferringTestBase {
    override func setUp() {
        continueAfterFailure = false
    }

    override class func testClass() -> AnyClass { TestExample.self }
}
