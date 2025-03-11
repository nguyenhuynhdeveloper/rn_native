import React from 'react';
import {NativeModules, Button, View, Text} from 'react-native';

import App_NativeModule from './src/module_android_Doc/App_NativeModule'; // file native module doc
import App_OpenActivity from './src/open_activity/App_OpenActivity';

const App = () => {
  // return <Text>xin chào </Text>;
  // return <App_NativeModule />; // Doc native module
  return <App_OpenActivity />; // Open Activity bên android
};

export default App;

/**

export JAVA_HOME=`/usr/libexec/java_home -v 11.0.15`

cần phải chạy trên java 11

 */
