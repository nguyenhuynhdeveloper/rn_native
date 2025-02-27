package com.rn_native;

import com.facebook.react.ReactActivity;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle; // Import this.
import android.content.Intent;


import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */



  @Override
  protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main)
  }


  @Override
  protected String getMainComponentName() {
    return "rn_native";
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
  }








}
