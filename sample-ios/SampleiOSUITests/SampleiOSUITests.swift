//  Copyright © 2020 Michal Laskowski. All rights reserved.

import sharedTests

final class SampleiOSUITests: DelegatingTestBase {
    override func setUp() {
        continueAfterFailure = false
    }

    override class func testClass() -> AnyClass { TestExample.self }
}
