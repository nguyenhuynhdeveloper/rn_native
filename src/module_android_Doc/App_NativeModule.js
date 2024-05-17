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



import CalendarModule from './BetterNativeModuleExport';

const App_NativeModule = () => {
  // const {CalendarModule} = NativeModules; // Lấy từ NativeModules ra sử dụng

  // const { BackPressReactModule } = NativeModules;



  const { DEFAULT_EVENT_NAME } = CalendarModule.getConstants();

  const onPress_runMethodSideNative = () => {
    console.log('_onPress_runMethodSideNative run !');
    CalendarModule.createCalendarEvent('testName', 'testLocation');

    console.log('DEFAULT_EVENT_NAME :', DEFAULT_EVENT_NAME);
  };

  const onPress_oneCallback = () => {
    console.log('_onPress_Callback run  !');
    CalendarModule.createCalendarEvent_Callback('testName', 'testLocation',
      eventId => {
        console.log(` Chạy callback khai báo bên RN -- Kích hoạt bên native : ${eventId}`);
      },);

    console.log('DEFAULT_EVENT_NAME :', DEFAULT_EVENT_NAME);
  };

  const onPress_oneCallback_logic_side_RN = () => {
    console.log('_onPress_oneCallback_logic_side_RN run  !');
    CalendarModule.createCalendarEvent_Callback_logicRN('testName', 'testLocation',
      (error, eventId) => {
        if (error) {  // Tham số 1 bên native đang là null
          console.error(`Callback của RN -- Kích hoạt từ native:: Error found! ${error}`);
        }
        console.log(`Callback của RN -- Kích hoạt từ native:: event id ${eventId} returned`);
      },);

    console.log('DEFAULT_EVENT_NAME :', DEFAULT_EVENT_NAME);
  };

  const onPress_twoCallback_logic_side_native = () => {
    console.log('_onPress_twoCallback_logic_side_native run  !');
    CalendarModule.createCalendarEvent_Callback_logicNative(
      'testName',
      'testLocation',
      error => {
        console.error(`Callback của RN -- Kích hoạt từ native: Error found! ${error}`);
      },
      eventId => {
        console.log(`Callback của RN -- Kích hoạt từ native: event id ${eventId} returned`);
      },
    );
  };


  const onPress_Promise = async () => {
    console.log('_onPress_Promise run  !');

    try {
      const eventId = await CalendarModule.createCalendarEvent_Promise(
        'Party',
        'My House',
      );  // Tham số thứ 3 là Promise không cần ghi -- Tham số thứ 3: chính là cái try-catch này 
      console.log(`Promise của RN được chạy -- kích hoạt bên native ${eventId}`);
    } catch (e) {
      console.error(e);
    }
  };

  // Biến mà chứa hàm lắng nghe sự kiện bên native 
  const eventEmitter = new NativeEventEmitter(NativeModules.CalendarModule);   // Phải gói NativeModule.CalendarModule bằng 

  useEffect(() => {
    let eventListener = eventEmitter.addListener('EventReminder', event => {
      // Có thể chạy hàm nào đó với dữ liệu được truyền sang từ bên native 
      console.log('Event emit from naitve: data : ', event.eventProperty); // Data được truyền sang từ native 

      // BackPressReactModule.goBack();
    });

    // Removes the listener once unmounted
    return () => {
      eventListener.remove();
    };
  }, []);

  const onPress_CreateEventFromNative = () => {
    CalendarModule.createCalendarEvent_CreateEventFromNative();
  };

  return (
    <View style={{ flex: 1 }}>
      <View style={{ height: 50 }} />

      <Button
        title="Click to invoke your native module!"
        color="#841584"
        onPress={onPress_runMethodSideNative}
      />

      <View style={{ height: 50 }} />

      <Button
        title="onPress_oneCallback !"
        color="#841584"
        onPress={onPress_oneCallback}
      />

      <View style={{ height: 50 }} />

      <Button
        title="onPress_oneCallback_logic_side_RN!"
        color="#841584"
        onPress={onPress_oneCallback_logic_side_RN}
      />
      <View style={{ height: 50 }} />

      <Button
        title="onPress_twoCallback_logic_side_native !"
        color="#841584"
        onPress={onPress_twoCallback_logic_side_native}
      />

      <View style={{ height: 50 }} />

      <Button
        title="onPress_Promise  !"
        color="#841584"
        onPress={onPress_Promise}
      />
      <View style={{ height: 50 }} />
      <Button
        title="onPress_CreateEventFromNative  !"
        color="#841584"
        onPress={onPress_CreateEventFromNative}
      />
      <View style={{ height: 50 }} />
      <TouchableOpacity
        style={{ height: 100, backgroundColor: 'pink' }}
        onPress={() => {
          // Gọi phương thức startNativeActivity từ Native Module
          console.log("Open Activity Android ");
          NativeModules.MyNativeModule.startNativeActivity();
        }}>
        <Text>Onclick </Text>
      </TouchableOpacity>

    </View>
  );
};

export default App_NativeModule;
