//
//  TestBase.m
//  SampleiOSUITests
//
//  Created by mlaskowski on 10/02/2020.
//  Copyright © 2020 Michal Laskowski. All rights reserved.
//

#import <Foundation/Foundation.h>
#include "TestBase.h"
#import "objc/runtime.h"

@implementation TestBaseForSelector

+ (XCTestCase *)testCaseForSelector:(SEL)selector {
    XCTFail("This method should be overriden");
    return nil;
}

/**
 This is overriden in order to be able to trigger tests from Test Navigator panel. Method can not be overriden in Swift, because it needs also
 to override `testCaseWithInvocation:`. And using NSInvocation is not possible in Swift. Hence this Obj-C TestBase class, and this method.
 */
+ (instancetype)testCaseWithSelector:(SEL)selector {
    return [self testCaseForSelector:selector];
}

@end
