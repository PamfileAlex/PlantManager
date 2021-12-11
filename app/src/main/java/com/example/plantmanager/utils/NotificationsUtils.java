package com.example.plantmanager.utils;

import android.app.Activity;
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
}
