import React from 'react';
import { NativeModules, Button, View, Text } from 'react-native';

import App_NativeModule from './src/module_android_Doc/App_NativeModule'; // file native module doc
import App_OpenActivity from './src/open_activity/App_OpenActivity';

// import App_ViewDoc from './src/view_android_Doc/App_ViewDoc';
// import App_AndroidFragment from './src/view_android_fragment_Doc/App_AndroidFragment';
// import App_ViewLibrary from './src/view_library_ad/App_ViewLibrary';

const App = () => {
  // return <Text>xin chào </Text>;
  // return <App_NativeModule />; // Doc native module
  // return <App_ViewLibrary />;
  // return <App_ViewDoc />;
  // return <App_AndroidFragment />;   // Đưa 1 Fragmnet vào thì ok 
  return <App_OpenActivity />;
};

export default App;
