import UIKit

@objc(RnDeviceBrightness)
class RnDeviceBrightness: NSObject {

  @objc(multiply:withB:withResolver:withRejecter:)
  func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
    resolve(a*b)
  }

  @objc(getBrightness:withRejecter:)
  func getBrightness(resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
      resolve(UIScreen.main.brightness)
  }

  @objc(setBrightness:)
  func setBrightness(level: Float) -> Void {

      DispatchQueue.main.async {
          UIScreen.main.brightness = CGFloat(level)
      }
  }

  @objc(setBrightnessAnimation:animationDuration:)
  func setBrightnessAnimation(level: Float, animationDuration: Float = 1000) -> Void {
      var currentBrightness: CGFloat = UIScreen.main.brightness
      let isIncreasing: Bool = currentBrightness < CGFloat(level)
      let incrementalStep: CGFloat = CGFloat(1 / animationDuration)

      DispatchQueue.global(qos: .userInteractive).async {
        while (isIncreasing && currentBrightness <= CGFloat(level)) || (!isIncreasing && currentBrightness >= CGFloat(level)) {
            DispatchQueue.main.async {
                currentBrightness += isIncreasing ? incrementalStep : -incrementalStep
                UIScreen.main.brightness = currentBrightness
            }

            Thread.sleep(forTimeInterval: 1 / 1000)
        }
    }
  }
}
