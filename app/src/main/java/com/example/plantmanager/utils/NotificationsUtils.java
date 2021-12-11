package com.example.plantmanager.utils;

import android.app.Activity;
import android.app.AlarmManager;
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
    public static void sendNotification(Activity activity) {
        Intent intent = new Intent(activity, Reminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);

        long currentTime = System.currentTimeMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime + 5000, pendingIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNotificationChannel(Activity activity) {
        NotificationChannel notificationChannel = new NotificationChannel("notifyWater", "WaterReminderChannel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    public static void showNotificationOld(Context context, String title, String message, Intent intent, int reqCode) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, reqCode, intent, PendingIntent.FLAG_ONE_SHOT);
        String CHANNEL_ID = "WaterReminderChannel";// The id of the channel.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(reqCode, notificationBuilder.build());
    }

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

        CharSequence name = "Channel Name";// The user-visible name of the channel.
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(mChannel);

        notificationManager.notify(reqCode, notificationBuilder.build());
    }
}
