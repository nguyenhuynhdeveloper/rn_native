package com.rn_native.BadgeModule;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BadgeModule extends ReactContextBaseJavaModule {
    private Context mContext;

    public BadgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "BadgeModule";
    }

    @ReactMethod
    public void setBadgeCount(int count) {
        // Code to set badge count
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", mContext.getPackageName());
        // intent.putExtra("badge_count_class_name", getLauncherClassName(mContext));
        mContext.sendBroadcast(intent);
    }

    private String getLauncherClassName(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = intent.resolveActivity(pm);
        if (cn != null) {
            return cn.getClassName();
        }
        return null;
    }
}