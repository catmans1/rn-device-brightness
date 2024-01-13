import * as React from 'react';

import { StyleSheet, View, Text, Button, Platform } from 'react-native';
import {
  getBrightness,
  getSystemBrightness,
  setBrightness,
  setBrightnessAnimation,
  setSystemBrightness,
} from 'rn-device-brightness';

export default function App() {
  const [level, setLevel] = React.useState<number | undefined>();

  React.useEffect(() => {
    if (Platform.OS === 'android') {
      getSystemBrightness().then(setLevel);
    } else {
      getBrightness().then(setLevel);
    }
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {level}</Text>

      <Button
        title="Set Brightness"
        onPress={() => {
          if (Platform.OS === 'android') {
            setSystemBrightness(0.5);
          } else {
            setBrightness(0.5);
          }
        }}
      />

      <Button
        title="Set Brightness Animation"
        onPress={() => {
          setBrightnessAnimation(1, 1000);
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'red',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
