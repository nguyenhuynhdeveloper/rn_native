import React from 'react';
import { NativeModules, Button, View, Text } from 'react-native';



import App_ViewDoc from './src/view_android_Doc/App_ViewDoc';
import App_AndroidFragment from './src/view_android_fragment_Doc/App_AndroidFragment';
import App_ViewLibrary from './src/view_library_ad/App_ViewLibrary';
import App_OpenActivity from './src/open_activity/App_OpenActivity';

const App = () => {
  // return <Text>xin chào </Text>;

  // return <App_ViewDoc />;    // Đưa 1 View instance vào RN
  return <App_AndroidFragment />;   // Đưa 1 Fragmnet vào thì ok 
  // return <App_ViewLibrary />;
  // return <App_OpenActivity />;
};

export default App;
