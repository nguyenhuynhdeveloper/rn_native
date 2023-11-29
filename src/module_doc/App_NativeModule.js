/**
 Các bước để có thể gửi view sang bên react native 
 1. Tạo 1 ViewManager 
 2. implement createViewInstance method
 3. Phơi bày thuộc tính view sủ dụng @ReactProp hoặc @ReactPropGroup
 4. Đăng ký quản lú createViewManager của application package
 5. Implement the JavaScript module 


 */

import React from 'react';
import {NativeModules, Button, View} from 'react-native';

const App_NativeModule = () => {
  const {CalendarModule} = NativeModules; // Lấy từ NativeModules ra sử dụng
  const onPress = () => {
    console.log('We will invoke the native module here!');
    CalendarModule.createCalendarEvent('testName', 'testLocation');
  };

  return (
    <View style={{flex: 1}}>
      <Button
        title="Click to invoke your native module!"
        color="#841584"
        onPress={onPress}
      />
    </View>
  );
};

export default App_NativeModule;
