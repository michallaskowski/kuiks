//  Copyright © 2020 Michal Laskowski. All rights reserved.

import sharedTests
import GCDWebServers

final class SampleiOSUITests: DelegatingTestBase {
    override func setUp() {
        super.setUp()
        continueAfterFailure = false
    }

    override class func testClass() -> AnyClass { TestListBehavior.self }
}

final class ContributorsUITests: DelegatingTestBase {
    override func setUp() {
        super.setUp()
        continueAfterFailure = false
    }

    override class func testClass() -> AnyClass { TestContributorsView.self }
}
