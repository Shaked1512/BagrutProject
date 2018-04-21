package com.appschool.bagrutproject.Shaked;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

/**
 * Created by pc on 21/04/2018.
 */

public class NotificationHelper extends ContextWrapper{

    private static final String SHAKED_CHANNEL_ID = "com.appschool.bagrutproject.Shaked.SHAKEDDEV";
    private static final String SHAKED_CHANNEL_NAME = "SHAKEDDEV Channel";
    private NotificationManager manager;
    public NotificationHelper(Context base)
    {
        super(base);
        createChannels();
    }

    private void createChannels() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel shakedChannel = new NotificationChannel(SHAKED_CHANNEL_ID,SHAKED_CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            shakedChannel.enableLights(true);
            shakedChannel.enableVibration(true);
            shakedChannel.setLightColor(Color.GREEN);
            shakedChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            getManager().createNotificationChannel(shakedChannel);
        }

    }


    public NotificationManager getManager() {
        if(manager == null)
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        return manager;
    }


    public Notification.Builder getSHAKEDChannelNotification(String title, String body, PendingIntent intent, long when, String ticker )
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(getApplicationContext(),SHAKED_CHANNEL_ID)
                    .setContentText(body)
                    .setContentTitle(title)
                    .setSmallIcon(android.R.drawable.star_on)
                    .setAutoCancel(true)
                    .setContentIntent(intent)
                    .setWhen(when)
                    .setTicker(ticker);
        }
        return null;
    }
}
