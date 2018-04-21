package com.appschool.bagrutproject.Shaked;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.appschool.bagrutproject.MainShaked;
import com.appschool.bagrutproject.R;

public class ActionPowerConnected extends AppCompatActivity {

    TextView tv;
    BroadCastPowerConnected broadCastPowerConnected;
    BroadCastPowerDisconnected broadCastPowerDisconnected;
    NotificationHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_power_connected);

        tv = (TextView)findViewById(R.id.tvDisplay);
        helper = new NotificationHelper(this);

        broadCastPowerConnected = new BroadCastPowerConnected();
        broadCastPowerDisconnected = new BroadCastPowerDisconnected();
    }
    private class BroadCastPowerConnected extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv.setText("TRUE");
            MakeNotification(context, "ActionPowerConnected", "Status", tv.getText().toString());
        }
    }

    private class BroadCastPowerDisconnected extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv.setText("FALSE");
            MakeNotification(context, "ActionPowerConnected", "Status", tv.getText().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadCastPowerConnected, new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(broadCastPowerDisconnected, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
        EndNotification();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadCastPowerConnected);
        unregisterReceiver(broadCastPowerDisconnected);
    }
    public void MakeNotification(Context context, String title, String ticker, String text)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(context, MainShaked.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            Notification.Builder builder1 = helper.getSHAKEDChannelNotification(title, text,pendingIntent, System.currentTimeMillis(), ticker);
            helper.getManager().notify(1, builder1.build());
        }
        else {
            Intent intent = new Intent(context, MainShaked.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            Notification notification = builder
                    .setContentIntent(pendingIntent)
                    .setTicker(ticker)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true).setContentTitle(title)
                    .setSmallIcon(android.R.drawable.star_on)
                    .setContentText(text).build();
            notificationManager.notify(1, notification);
        }
    }
    public void EndNotification()
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }

}
