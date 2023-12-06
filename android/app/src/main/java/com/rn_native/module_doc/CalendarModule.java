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


public class CalendarModule extends ReactContextBaseJavaModule {
   CalendarModule(ReactApplicationContext context) {
       super(context);
   }
   // add to CalendarModule.java
@Override
public String getName() {
   return "CalendarModule";
}

@ReactMethod
public void createCalendarEvent(String name, String location) {
   Log.d("CalendarModule", "Create event called with name: " + name
   + " and location: " + location);
}

// Có thể export Constant từ bên native sang bên RN
   @Override
   public Map<String, Object> getConstants() {
      final Map<String, Object> constants = new HashMap<>();
      constants.put("DEFAULT_EVENT_NAME", "New Event");
      return constants;
   }

   // Callback
   // native module cũng hỗ trợ 1 loại tham số nữa là callback để có thể từ native kích hoạt chạy lại 1 hàm gì đó phía bên RN
   @ReactMethod
   public void createCalendarEvent_Callback(String name, String location, Callback callBack) {
      Log.d("CalendarModule", "createCalendarEvent_Callback: " + name
              + " and location: " + location);
      Integer eventId = 100;
      callBack.invoke(eventId);
      WritableMap params = Arguments.createMap();
      params.putString("eventProperty", "someValue");

      sendEvent(getReactApplicationContext(), "EventReminder", params);
   }


   // Có 1 lưu ý quan trọng đó là tại 1 thời điểm chỉ chạy được 1 callback , có nghĩa là chỉ có thể chạy cái success hoặc fail không thể chạy cả 2
   // Có 2 cách để implement :

      // Cách 1 : Logic ở bên RN
   @ReactMethod
   public void createCalendarEvent_Callback_logicRN(String name, String location, Callback callBack) {
      Log.d("CalendarModule", "createCalendarEvent_Callback_logicRN: " + name
              + " and location: " + location);
      Integer eventId = 100;
      callBack.invoke(null, eventId);  // Tham số đầu tiên quyết định sẽ chạy hàm nào bên React native

   }

   // Cách 2 : Logic ở bên native
   @ReactMethod
   public void createCalendarEvent_Callback_logicNative(String name, String location, Callback myFailureCallback, Callback mySuccessCallback) {
      Log.d("CalendarModule", "createCalendarEvent_Callback_logicRN: " + name
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
      try {
         Integer eventId = 100;
         promise.resolve(eventId);
      } catch(Exception e) {
         promise.reject("Create Event Error", e);
      }
   }

   // Sending event to javascript
   // Để có thể kích hoạt 1 event mà bên RN đăng ký nhận event đó phát ra từ bên nnative

   private void sendEvent(ReactContext reactContext,
                          String eventName,
                          @Nullable WritableMap params) {
      reactContext
              .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
              .emit(eventName, params);
   }
}