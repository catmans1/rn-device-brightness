#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RnDeviceBrightness, NSObject)

RCT_EXTERN_METHOD(multiply:(float)a withB:(float)b
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(getBrightness:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(setBrightness:(float)level)

RCT_EXTERN_METHOD(setBrightnessAnimation:(float)level animationDuration:(CGFloat)animationDuration)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
