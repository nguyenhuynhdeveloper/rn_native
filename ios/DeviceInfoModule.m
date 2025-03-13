#import "DeviceInfoModule.h"
#import <React/RCTLog.h>
#import <UIKit/UIKit.h>

@implementation DeviceInfoModule

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getOSVersion:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    NSString *osVersion = [[UIDevice currentDevice] systemVersion];
    if (osVersion) {
        resolve(osVersion);
    } else {
        reject(@"OS_VERSION_ERROR", @"Could not get OS version", nil);
    }
}

@end
