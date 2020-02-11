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

+(Class)testClass {
    // needs to be overriden
    return nil;
}

+ (XCTestCase *)testCaseForSelector:(SEL)selector {
    // needs to be overriden
    return nil;
}

+ (NSArray<NSString *> *)registerTestMethods {
    // needs to be overriden
    return @[];
}

+(instancetype)init {
    return [super init];
}

+(instancetype)testCaseWithInvocation:(NSInvocation *)invocation {
    return [super testCaseWithInvocation:invocation];
}

// added because otherwise main test button does not find tests
+(NSArray<NSInvocation *> *)testInvocations {
    Class testClass = [self testClass];
    if (testClass == nil) {
        return @[];
    }
    NSArray<NSString *> *stringSelectors = [self registerTestMethods];

    NSMutableArray<NSInvocation *> *invocations = [NSMutableArray arrayWithCapacity:[stringSelectors count]];
    for (NSString *stringSelector in stringSelectors) {
        SEL selector = NSSelectorFromString(stringSelector);
        NSMethodSignature *methodSignature = [testClass instanceMethodSignatureForSelector:selector];
        NSInvocation * invocation = [NSInvocation invocationWithMethodSignature:methodSignature];
        [invocations addObject:invocation];
    }
    return invocations;
}

/**
 This is overriden in order to be able to trigger tests from Test Navigator panel. Method can not be overriden in Swift, because it needs also
 to override `testCaseWithInvocation:`. And using NSInvocation is not possible in Swift. Hence this Obj-C TestBase class, and this method.
 */
+ (instancetype)testCaseWithSelector:(SEL)selector {
    return [self testCaseForSelector:selector];
}

@end
