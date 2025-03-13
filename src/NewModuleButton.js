import React from 'react';
import {Button, View} from 'react-native';
import {NativeModules} from 'react-native';
import NativeCalendarModule from './NativeCalendarModule';

const {CalendarModule} = NativeModules;

const {DEFAULT_EVENT_NAME} = CalendarModule.getConstants();
console.log('DEFAULT_EVENT_NAME: ', DEFAULT_EVENT_NAME);

const NewModuleButton = () => {
  const onPress = () => {
    console.log('We will invoke the native module here!');
    // CalendarModule.createCalendarEvent('testName', 'testLocation');
    NativeCalendarModule.createCalendarEvent('foo', 'bar');
  };

  const onSubmit = () => {
    CalendarModule.createCalendarEventHaveCallback(
      'Party',
      '04-12-2020',
      eventId => {
        console.log(`Created a new event with id ${eventId}`); // Hàm callback được định nghĩa ở đây , và cũng sẽ được chạy ở bên js
      },
    );
  };

  const onPressLogicSideJs = () => {
    CalendarModule.createCalendarEventCallback(
      'testName',
      'testLocation',
      (error, eventId) => {
        if (error) {
          console.error(`Error found! ${error}`);
        }
        console.log(`event id ${eventId} returned`);
      },
    );
  };

  const onPressLogicSideIOS = () => {
    CalendarModule.createCalendarEventCallbackLogicSideIOS(
      'testName',
      'testLocation',
      error => {
        console.error(`Error found! ${error}`);
      },
      eventId => {
        console.log(`event id ${eventId} returned`);
      },
    );
  };

  const onSubmitPromiseSideIOS = async () => {
    try {
      const eventId = await CalendarModule.createCalendarEventPromise(
        'Party',
        'my house',
      );
      console.log(`Created a new event with id ${eventId}`);
    } catch (e) {
      console.error(e);
    }
  };

  return (
    <View
      style={{
        justifyContent: 'flex-start',
        alignItems: 'flex-start',
      }}>
      <Button
        title="Click to invoke your native module!"
        color="#841584"
        onPress={onPress}
      />
      <Button
        title="Click to invoke your native module have callback!"
        color="#841584"
        onPress={onSubmit}
      />

      <Button
        title="Callback tham số bên ios , logic bên js!"
        color="#841584"
        onPress={onPressLogicSideJs}
      />

      <Button
        title="Callback tham số bên js , logic bên ios!"
        color="#841584"
        onPress={onPressLogicSideIOS}
      />

      {/* <Button
        title=" Promise in IOS!"
        color="#841584"
        onPress={onSubmitPromiseSideIOS}
      /> */}
    </View>
  );
};

export default NewModuleButton;
