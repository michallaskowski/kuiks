//  Created by Michał Laskowski on 10/02/2020.
//  Copyright © 2020 Michał Laskowski. All rights reserved.

#ifndef TestBase_h
#define TestBase_h
@import XCTest;

NS_ASSUME_NONNULL_BEGIN

@interface TestBaseForSelector : XCTestCase

+(void)registerTestFor:(SEL)selector;

@end

NS_ASSUME_NONNULL_END

#endif /* TestBase_h */
