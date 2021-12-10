package com.example.plantmanager.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotificationsUtils {
    public static void sendNotification(Activity activity) {
        Intent intent = new Intent(activity, Reminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);

        long currentTime = System.currentTimeMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime+5000, pendingIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNotificationChannel(Activity activity) {
        NotificationChannel notificationChannel = new NotificationChannel("notifyWater", "WaterReminderChannel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }
}
