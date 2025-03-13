import React from 'react';
import type {Node} from 'react';
import {SafeAreaView, StyleSheet} from 'react-native';

import NewModuleButton from './src/NewModuleButton';

const App: () => Node = () => {
  return (
    <SafeAreaView>
      <NewModuleButton />
    </SafeAreaView>
  );
};

export default App;
