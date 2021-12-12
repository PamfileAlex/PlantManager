package com.example.plantmanager.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.plantmanager.R;
import com.example.plantmanager.models.Plant;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class NotificationsUtils {
    public static final String NOTIFICATION_CHANNEL_ID = "WaterReminderChannel";
    private final static String default_notification_channel_id = "default";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void scheduleNotification(Activity activity, String title, String message, LocalDateTime localDateTime) {
        createNotificationChannel(activity);

        Intent notificationIntent = new Intent(activity, NotificationReceiver.class);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION, getNotification(activity, title, message));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;

        long time = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    private static Notification getNotification(Activity activity, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, default_notification_channel_id);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createNotificationChannel(Activity activity) {
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "WaterReminderChannel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void triggerNotification(Activity activity, Plant plant){
        LocalDateTime localDateTime = LocalDateTime.of(plant.getNextWater(), plant.getTime());
        NotificationsUtils.scheduleNotification(activity, "PlantManager", "It's time to water your " + plant.getName() + "!", localDateTime);
    }
}
