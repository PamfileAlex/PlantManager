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

public class NotificationsUtils {
    public static final String NOTIFICATION_CHANNEL_ID = "WaterReminderChannel";
    private final static String default_notification_channel_id = "default";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void showNotification(Activity activity, String title, String message, int reqCode) {
        Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, reqCode, intent, PendingIntent.FLAG_ONE_SHOT);
        String CHANNEL_ID = "WaterReminderChannel";// The id of the channel.

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence name = "PlantManager";// The user-visible name of the channel.
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(mChannel);

        notificationManager.notify(reqCode, notificationBuilder.build());
    }

    public static void scheduleNotification(Activity activity, long delay) {
        System.out.println("ENTER SCHEDULE NOTIFICATION");
        Intent notificationIntent = new Intent(activity, NotificationReceiver.class);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION, getNotification(activity, "This is a notification"));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent);
        System.out.println("EXIT SCHEDULE NOTIFICATION");
    }

    private static Notification getNotification(Activity activity, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, default_notification_channel_id);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNotificationChannel(Activity activity) {
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "WaterReminderChannel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }
}
