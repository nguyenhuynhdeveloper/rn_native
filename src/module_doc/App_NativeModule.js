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
} from 'react-native';

import CalendarModule from './BetterNativeModuleExport';

const App_NativeModule = () => {
  // const {CalendarModule} = NativeModules; // Lấy từ NativeModules ra sử dụng

  const { DEFAULT_EVENT_NAME } = CalendarModule.getConstants();

  const onPress = () => {
    console.log('We will invoke the native module here!');
    CalendarModule.createCalendarEvent('testName', 'testLocation');

    console.log('DEFAULT_EVENT_NAME :', DEFAULT_EVENT_NAME);
  };

  const onPress_Callback = () => {
    console.log('We will invoke the native module here!');
    CalendarModule.createCalendarEvent_Callback('testName', 'testLocation',
      eventId => {
        console.log(`Created a new event with id ${eventId}`);
      },);

    console.log('DEFAULT_EVENT_NAME :', DEFAULT_EVENT_NAME);
  };

  const onPress_Callback_side_RN = () => {
    console.log('We will invoke the native module here!');
    CalendarModule.createCalendarEvent_Callback_logicRN('testName', 'testLocation',
      (error, eventId) => {
        if (error) {  // Tham số 1 bên native đang là null
          console.error(`Callback side RN: Error found! ${error}`);
        }
        console.log(`Callback side RN: event id ${eventId} returned`);
      },);

    console.log('DEFAULT_EVENT_NAME :', DEFAULT_EVENT_NAME);
  };

  const onPress_Callback_side_Native = () => {
    CalendarModule.createCalendarEvent_Callback_logicNative(
      'testName',
      'testLocation',
      error => {
        console.error(`Callback side Native: Error found! ${error}`);
      },
      eventId => {
        console.log(`Callback side Native: event id ${eventId} returned`);
      },
    );
  };


  const onPress_Promise = async () => {
    try {
      const eventId = await CalendarModule.createCalendarEvent_Promise(
        'Party',
        'My House',
      );  // Tham số thứ 3 là Promise không cần ghi 
      console.log(`Created a new event with id ${eventId}`);
    } catch (e) {
      console.error(e);
    }
  };

  const eventEmitter = new NativeEventEmitter(NativeModules.CalendarModule);   // Phải gói NativeModule.CalendarModule bằng 
  useEffect(() => {
    let eventListener = eventEmitter.addListener('EventReminder', event => {
      console.log('Event emit from naitve ', event.eventProperty); // "someValue"
    });

    // Removes the listener once unmounted
    return () => {
      // eventListener.remove();
    };
  }, []);

  return (
    <View style={{ flex: 1 }}>
      <View style={{ height: 50 }} />

      <Button
        title="Click to invoke your native module!"
        color="#841584"
        onPress={onPress}
      />

      <View style={{ height: 50 }} />

      <Button
        title="Callback Module !"
        color="#841584"
        onPress={onPress_Callback}
      />

      <View style={{ height: 50 }} />

      <Button
        title="Callback Module logic side RN!"
        color="#841584"
        onPress={onPress_Callback_side_RN}
      />
      <View style={{ height: 50 }} />

      <Button
        title="Callback Module logic side native !"
        color="#841584"
        onPress={onPress_Callback_side_Native}
      />

      <View style={{ height: 50 }} />

      <Button
        title="Promise Module  !"
        color="#841584"
        onPress={onPress_Promise}
      />

    </View>
  );
};

export default App_NativeModule;
