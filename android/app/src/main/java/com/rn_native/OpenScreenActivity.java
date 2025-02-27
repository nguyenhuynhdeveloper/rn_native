package com.rn_native;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.rn_native.sakura.felica.ui.ActivityReadNfc;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class OpenScreenActivity extends ReactContextBaseJavaModule {
    OpenScreenActivity(ReactApplicationContext context) {
        super(context);
    }

    // add to CalendarModule.java
    @Override
    public String getName() {
        return "OpenScreenActivity";
    }

    @ReactMethod
    public void createCalendarEvent(String name, String location) {
        Log.d("CalendarModule", "Create event called with name: " + name
                + " and location: " + location);

        Activity activity = getCurrentActivity();
        if (activity != null) {
            Log.d("FROM_RN", "----");
            Intent intent = new Intent(activity, ActivityReadNfc.class);
            activity.startActivity(intent);
        }

    }
}