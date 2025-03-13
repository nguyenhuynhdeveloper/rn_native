import React from 'react';
import type {Node} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  TouchableOpacity,
  useColorScheme,
  NativeModules,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';

const {OpenActivityModule} = NativeModules;

const App_OpenActivity: () => Node = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <TouchableOpacity
        style={{height: 100, backgroundColor: 'pink'}}
        onPress={() => {
          // Gọi phương thức startNativeActivity từ Native Module đê mở 1 activity bên phía android
          console.log('CLick ');
          OpenActivityModule.startNativeActivity();
        }}>
        <Text>Onclick </Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App_OpenActivity;
