import React from 'react';
import { NativeModules, Button, View } from 'react-native';

import App_NativeModule from './src/module_doc/App_NativeModule'; // file native module doc
import BadgeModuleApp from './src/badgeModule/BadgeModule_App';


const App = () => {
  // return <Text>xin chào </Text>;
  // return <App_NativeModule />; // Doc native module
  return <BadgeModuleApp />; // Doc native module

};

export default App;
