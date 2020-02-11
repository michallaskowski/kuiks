//
//  TestBase.h
//  SampleiOS
//
//  Created by mlaskowski on 10/02/2020.
//  Copyright © 2020 Michal Laskowski. All rights reserved.
//

#ifndef TestBase_h
#define TestBase_h
@import XCTest;

@interface TestBaseForSelector : XCTestCase

+(XCTestCase *)testCaseForSelector:(SEL)selector;

@end

#endif /* TestBase_h */
