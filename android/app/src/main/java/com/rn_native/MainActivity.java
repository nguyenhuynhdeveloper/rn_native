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

  private BadgeUpdateReceiver badgeUpdateReceiver;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);

    // Register broadcast receiver
    // super.onCreate(savedInstanceState);
    badgeUpdateReceiver = new BadgeUpdateReceiver();
    IntentFilter intentFilter = new IntentFilter("android.intent.action.BADGE_COUNT_UPDATE");
    registerReceiver(badgeUpdateReceiver, intentFilter);


    // Create a notification channel for Android Oreo and higher
    createNotificationChannel();

    // Initialize notification manager
    notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
  }


  @Override
  protected String getMainComponentName() {
    return "rn_native";
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
    // Unregister broadcast receiver to avoid memory leaks
    if (badgeUpdateReceiver != null) {
      unregisterReceiver(badgeUpdateReceiver);
    }
  }

  // Define BroadcastReceiver to handle badge count updates
  private class BadgeUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      if (intent.getAction() != null && intent.getAction().equals("android.intent.action.BADGE_COUNT_UPDATE")) {
        int badgeCountNum = intent.getIntExtra("badge_count", 0);
        String packageName = intent.getStringExtra("badge_count_package_name");
        String className = intent.getStringExtra("badge_count_class_name");

        // Handle the badge count update here
        // For example, you can update your app's launcher icon with the received badge count
        // You may need to use a custom implementation based on your launcher and device compatibility
       Toast.makeText(context, "Received badge count update: " + badgeCount, Toast.LENGTH_SHORT).show();

//        int badgeCount = 5; // Set your desired badge count here

        // Update badge count if supported
//        setBadgeCount(getApplicationContext(), badgeCount);

        // Update badge count
//        ShortcutBadger.applyCount(getApplicationContext(), badgeCount);

        badgeCount = badgeCountNum;
        updateNotificationBadge();
      }

    }
  }


  private void setBadgeCount(Context context, int count) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
      NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
      notificationManager.createNotificationChannel(channel);
    }

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.cpb_background)
            .setContentTitle("Title")
            .setContentText("Content");

    if (count > 0) {
    Log.d("Badge", "num");
      builder.setNumber(count);
    }

    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(0, builder.build());
  }

  private static final String CHANNEL_ID = "badge_channel";
  private int badgeCount = 0;
  private NotificationManager notificationManager;

  private  void updateNotificationBadge() {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.cpb_background)
            .setContentTitle("Badge Count Example")
            .setContentText("Badge Count: " + badgeCount)
            .setNumber(500)
            .setAutoCancel(true);

    Notification notification = builder.build();

    // Use the same notification ID to update existing notification
    notificationManager.notify(0, notification);
  }

  private void createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      CharSequence name = getString(R.string.app_name);
      String description = getString(R.string.app_name);
      int importance = NotificationManager.IMPORTANCE_DEFAULT;
      NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
      channel.setDescription(description);

      // Register the channel with the system
      notificationManager = getSystemService(NotificationManager.class);
      notificationManager.createNotificationChannel(channel);
    }
  }



}
