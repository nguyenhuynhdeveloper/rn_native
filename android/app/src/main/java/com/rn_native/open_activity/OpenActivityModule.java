package com.rn_native.open_activity;     // Trong file OpenActivityModule.java

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class OpenActivityModule extends ReactContextBaseJavaModule {

    public OpenActivityModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "OpenActivityModule";
    }

    @ReactMethod
    public void startNativeActivity() {
        Log.d("FROM_RN", "----");

       Activity activity = getCurrentActivity();
       if (activity != null) {
           Log.d("FROM_RN", "----");
           Intent intent = new Intent(activity, OpenActivity.class);
           activity.startActivity(intent);
       }
    }
}
