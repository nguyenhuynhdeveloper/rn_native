import React from 'react';
import {NativeModules, Button, View} from 'react-native';

import App_NativeModule from './src/module_doc/App_NativeModule'; // file native module doc

import App_ViewDoc from './src/view_doc/App_ViewDoc';
import App_AndroidFragment from './src/view_android_fragment/App_AndroidFragment';
import App_ViewLibrary from './src/view_library_ad/App_ViewLibrary';
import App_OpenActivity from './src/open_activity/App_OpenActivity';

const App = () => {
  // return <Text>xin chào </Text>;
  // return <App_NativeModule />;
  // return <App_ViewLibrary />;
  // return <App_ViewDoc />;
  return <App_AndroidFragment />;
  // return <App_OpenActivity />;
};

export default App;
