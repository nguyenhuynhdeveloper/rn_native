import React from 'react';
import type {Node} from 'react';
import {SafeAreaView, StyleSheet} from 'react-native';

import NewModuleButton from './src/NewModuleButton';

import {NativeModules} from 'react-native';
const {DeviceInfoModule} = NativeModules;

const App: () => Node = () => {
  const getOSVersion = async () => {
    try {
      const osVersion = await DeviceInfoModule.getOSVersion();
      console.log('OS Version:', osVersion);
    } catch (e) {
      console.error(e);
    }
  };

  // Gọi hàm
  getOSVersion();
  return (
    <SafeAreaView>
      <NewModuleButton />
    </SafeAreaView>
  );
};

export default App;
