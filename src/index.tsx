import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'rn-device-brightness' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const RnDeviceBrightness = NativeModules.RnDeviceBrightness
  ? NativeModules.RnDeviceBrightness
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return RnDeviceBrightness.multiply(a, b);
}

export function getBrightness(): Promise<number> {
  return RnDeviceBrightness.getBrightness();
}

/**
 * This method is only available on Android
 * @returns System Brightness value between 0..1
 **/

export function getSystemBrightness(): Promise<number> {
  if (Platform.OS !== 'android') {
    throw Error('⚠️ This method is only available on Android ⚠️');
  }

  return RnDeviceBrightness.getSystemBrightness();
}

/**
 * @param value brightness value between 0..1
 * @returns Void
 **/
export function setBrightness(value: number): Promise<void> {
  if (value < 0 || value > 1) {
    if (!(Platform.OS === 'android' && value === -1)) {
      throw Error('⚠️ Brightness value must betweens 0 to 1 ⚠️');
    }
  }

  return RnDeviceBrightness.setBrightness(value);
}

/**
 * This method is only available on Android
 * @param value brightness value between 0..1
 * @returns Void
 **/
export function setSystemBrightness(value: number): Promise<void> {
  if (Platform.OS !== 'android') {
    throw Error('⚠️ This method is only available on Android ⚠️');
  }

  if (value < 0 || value > 1) {
    if (!(Platform.OS === 'android' && value === -1)) {
      throw Error('⚠️ Brightness value must betweens 0 to 1 ⚠️');
    }
  }

  return RnDeviceBrightness.setSystemBrightness(value);
}

/**
 * @param value brightness value between 0..1
 * @param animationDuration animation duration in milliseconds
 * @returns Void
 **/
export function setBrightnessAnimation(
  value: number,
  animationDuration: number
): Promise<void> {
  if (value < 0 || value > 1) {
    if (!(Platform.OS === 'android' && value === -1)) {
      throw Error('⚠️ Brightness value must betweens 0 to 1 ⚠️');
    }
  }

  if (animationDuration <= 0) {
    throw Error('⚠️ AnimationDuration value must greater than 0 ⚠️');
  }

  return RnDeviceBrightness.setBrightnessAnimation(value, animationDuration);
}
