# rn-device-brightness

A native module for React Native to control the screen brightness.

## Installation

```sh
npm install rn-device-brightness
```
or
```sh
yarn add rn-device-brightness
```

## Setup
### Android
- Remember add this code in your `AndroidManifest.xml`

```<uses-permission android:name="android.permission.WRITE_SETTINGS" />```
### iOS
- Run pod
  - `cd ios && pod install && cd ..` or `npx pod-install`
## Function

<strong>`getBrightness`</strong>: support for Android and iOS, will get brightness level

<strong>`getSystemBrightness`</strong>: support Android only, will get brightness level

<strong>`setBrightness`</strong>: support for Android and iOS, set brightness

<strong>`setBrightnessAnimation`</strong>: support for (Android and iOS), set brightness with animation

<strong>`setSystemBrightness`</strong>: support for Android, set system brightness

## Usage

```js
import { getBrightness, setBrightness } from 'rn-device-brightness';

// ...

const level = await getBrightness();
setBrightness(0.75)
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
