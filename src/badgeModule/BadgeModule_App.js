/* eslint-disable prettier/prettier */
/**
 Các bước để có thể gửi view sang bên react native 
 1. Tạo 1 ViewManager 
 2. implement createViewInstance method
 3. Phơi bày thuộc tính view sủ dụng @ReactProp hoặc @ReactPropGroup
 4. Đăng ký quản lú createViewManager của application package
 5. Implement the JavaScript module 
 */

import React, { useEffect } from 'react';
import {
  NativeModules,
  NativeEventEmitter,
  Button,
  TouchableOpacity,
  View,
  Text,
} from 'react-native';






const BadgeModuleApp = () => {
  const BadgeModule = NativeModules.BadgeModule;



  const onPressNum = (num) => {
    console.log('setBadgeCount onPressNum  num ');
    // Call the method to set badge count
    BadgeModule.setBadgeCount(num); // Set the desired badge count
  };




  return (
    <View style={{ flex: 1 }}>
      <View style={{ height: 50 }} />

      <TouchableOpacity
        // title="Click to setBadge for android "
        color="#841584"
        onPress={() => onPressNum(5)}
        style={{
          height: 50,
          backgroundColor: 'red',
        }}
      >
        <View style={{ flex: 1 }}>
          <Text style={{ flex: 1 }}>xin chafo </Text>
        </View>
      </TouchableOpacity>

      <View style={{ height: 50 }} />

      <TouchableOpacity
        // title="Click to setBadge for android "
        color="#841584"
        onPress={() => onPressNum(50)}
        style={{
          height: 50,
          backgroundColor: 'red',
        }}
      >
        <View style={{ flex: 1 }}>
          <Text style={{ flex: 1 }}>xin chafo </Text>
        </View>
      </TouchableOpacity>

      <View style={{ height: 50 }} />

      <TouchableOpacity
        // title="Click to setBadge for android "
        color="#841584"
        onPress={() => onPressNum(500)}
        style={{
          height: 50,
          backgroundColor: 'red',
        }}
      >
        <View style={{ flex: 1 }}>
          <Text style={{ flex: 1 }}>xin chafo </Text>
        </View>
      </TouchableOpacity>

      <View style={{ height: 50 }} />


    </View>
  );
};

export default BadgeModuleApp;
