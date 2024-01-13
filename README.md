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

##### iOS

`cd ios && pod install && cd ..` or `npx pod-install`

## Permission
##### Android
- Remember add the following uses-permission to your `AndroidManifest.xml` (usually found at: android/src/main/)

```sh
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
```


### Running the example project


`cd example && yarn install`

`npx pod-install && yarn ios` or `yarn android`

### Function

<strong>getBrightness</strong>: support Android and iOS, get brightness level

<strong>getSystemBrightness</strong>: support Android only, get brightness level

<strong>setBrightness</strong>: support Android and iOS, set brightness

<strong>setBrightnessAnimation</strong>: support Android and iOS, set brightness with animation

<strong>setSystemBrightness</strong>: support Android, set system brightness

## Usage

```js
import { Platform } from 'react-native';
import { getBrightness, setBrightness, setSystemBrightnness } from 'rn-device-brightness';

// ...

const level = await getBrightness();

if (Platform.OS === 'android') {
  setSystemBrightnness(0.75);
} else {
  setBrightness(0.75);
}
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
