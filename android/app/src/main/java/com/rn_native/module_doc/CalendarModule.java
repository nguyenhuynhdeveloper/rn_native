package com.rn_native.module_doc;// replace your-apps-package-name with your app’s package name
import com.facebook.react.bridge.Callback;  // Thêm vào để có thể sử dụng callback
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;  // Thêm để cố thể sử dụng promise
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

import com.facebook.react.modules.core.DeviceEventManagerModule;  // Để có thể send event to JS
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import android.util.Log;

import androidx.annotation.Nullable;

// https://reactnative.dev/docs/native-modules-android
// Làm theo hướng dẫn của Doc React Native 

public class CalendarModule extends ReactContextBaseJavaModule {

   private static ReactApplicationContext reactContext;
   public CalendarModule(ReactApplicationContext context) {
       super(context);
      reactContext = context;
   }

   // add to CalendarModule.java
   public static  final  String TAG = "CalendarModule ";

@Override
public String getName() {
   return "CalendarModule";
}


// Method : chạy 1 hàm bên native android
@ReactMethod
public void createCalendarEvent(String name, String location) {
   Log.d(TAG, "Create event called with name: " + name
   + " and location: " + location);
}

   // Exporting Constants : Có thể export Constant từ bên native sang bên RN -- bên RN có thể lấy dùng
   @Override
   public Map<String, Object> getConstants() {
      final Map<String, Object> constants = new HashMap<>();
      constants.put("DEFAULT_EVENT_NAME", " Constants side native Android");
      return constants;
   }

   // Callback : native module cũng hỗ trợ 1 loại tham số nữa là callback để có thể từ native kích hoạt chạy lại 1 hàm gì đó phía bên RN
   @ReactMethod
   public void createCalendarEvent_Callback(String name, String location, Callback callBack) {   //Tham số 3: Hàm RN truyền sang
      Log.d(TAG, "createCalendar have_run_Callback_side_RN: " + name
              + " and location: " + location);
      Integer eventId = 100;
      callBack.invoke(eventId);   //  Chạy hàm bên RN truyền sang -- Chạy bên RN

//      WritableMap params = Arguments.createMap();
//      params.putString("eventProperty", "someValue");
//      sendEvent(getReactApplicationContext(), "EventReminder", params);   //Tham số 2:  Tên Event -Tham số 3:  Tham số gửi sang RN
   }


   // Có 1 lưu ý quan trọng đó là tại 1 thời điểm chỉ chạy được 1 callback , có nghĩa là chỉ có thể chạy cái success hoặc fail không thể chạy cả 2
   // Có 2 cách để implement :

   // Cách 1 : Logic ở bên RN
   @ReactMethod
   public void createCalendarEvent_Callback_logicRN(String name, String location, Callback callBack) {
      Log.d(TAG, "createCalendarEvent_Callback_logicRN: " + name
              + " and location: " + location);
      Integer eventId = 100;
      callBack.invoke(null, eventId);  // Tham số đầu tiên quyết định sẽ chạy hàm nào bên React native

   }

   // Cách 2 : Logic ở bên native
   @ReactMethod
   public void createCalendarEvent_Callback_logicNative(String name, String location, Callback myFailureCallback, Callback mySuccessCallback) {
      Log.d(TAG, "createCalendarEvent_Callback_logicRN: " + name
              + " and location: " + location);
      Integer eventId = 100;

      if(eventId == 100) {
         mySuccessCallback.invoke(eventId);
      }else{
         myFailureCallback.invoke(eventId);
      }


   }

   // Promise: Trả ra 1 promise để bên RN có thể async/ await để có thể lấy về kết quả
   @ReactMethod
   public void createCalendarEvent_Promise(String name, String location, Promise promise) {
      Log.d(TAG, "_createCalendarEvent_Promise run in native : " );
      try {
         Integer eventId = 100;
         promise.resolve(eventId);
      } catch(Exception e) {
         promise.reject("Create Event Error", e);
      }
   }

   // Sending event to javascript
   // Để có thể kích hoạt 1 event mà bên RN đăng ký nhận event đó phát ra từ bên native


   // Hàm sendEvent : của DOC 
   private void sendEvent(ReactContext reactContext,
                          String eventName,
                          @Nullable WritableMap params) {
      reactContext
              .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
              .emit(eventName, params);  // Truyền tên Event - Tham số  : sẽ chạy bên React native
   }

   @ReactMethod
   public void createCalendarEvent_CreateEventFromNative() {   //Tham số 3: Hàm RN truyền sang
      Log.d(TAG, "_createCalendarEvent_CreateEventFromNative run in native : " );


      WritableMap params = Arguments.createMap();
      params.putString("eventProperty", "someValue");
      sendEvent(getReactApplicationContext(), "EventReminder", params);   //Tham số 2:  Tên Event -Tham số 3:  Tham số gửi sang RN
   }


   // Hàm sendEvent lược bỏ tham số ReactConText -- 
   public static void sendEvent(String eventName, String eventData) {
      reactContext
              .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
              .emit(eventName, eventData);
   }

}