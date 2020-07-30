//
//  TestBase.m
//  SampleiOSUITests
//
//  Created by mlaskowski on 10/02/2020.
//  Copyright © 2020 Michal Laskowski. All rights reserved.
//

#import <Foundation/Foundation.h>
#include "TestBaseForSelector.h"
#import "objc/runtime.h"

@implementation TestBaseForSelector

/// needs to be overriden in order for testCaseWithSelector: to work
+(void)registerTestFor:(SEL)selector {}

/**
 This is overriden in order to be able to trigger tests from Test Navigator panel. Method can not be overriden in Swift, because it needs also
 to override `testCaseWithInvocation:`. And using NSInvocation is not possible in Swift. Hence this Obj-C TestBase class, and this method.
 */
+(instancetype)testCaseWithSelector:(SEL)selector {
    [self registerTestFor:selector];
    return [super testCaseWithSelector:selector];
}

@end
