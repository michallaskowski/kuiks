//  Copyright © 2020 Michal Laskowski. All rights reserved.

import sharedTests
import NativeTestBase

final class SampleiOSUITests: DelegatingTestBase {
    override class func testClass() -> AnyClass { TestListBehavior.self }
}

final class ContributorsUITests: DelegatingTestBase {
    override class func testClass() -> AnyClass { TestContributorsView.self }
}
