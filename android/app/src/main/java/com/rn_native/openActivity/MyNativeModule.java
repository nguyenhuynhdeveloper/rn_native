package com.rn_native.openActivity;     // Trong file MyNativeModule.java

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class MyNativeModule extends ReactContextBaseJavaModule {

    public MyNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "MyNativeModule";
    }

    @ReactMethod
    public void startNativeActivity() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            Log.d("FROM_RN", "----");
            Intent intent = new Intent(activity, OpenActivity.class);
            activity.startActivity(intent);
        }
    }
}
