package com.appschool.bagrutproject.Shaked;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.appschool.bagrutproject.MainShaked;
import com.appschool.bagrutproject.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ActionPowerDisconnected extends AppCompatActivity {

    TextView tv;
    BroadCastPowerConnected broadCastPowerConnected;
    BroadCastPowerDisconnected broadCastPowerDisconnected;
    NotificationHelper helper;
    ArrayList<Broadcast> broadcasts;
    BroadcastHelper broadcastHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_power_disconnected);
        tv = (TextView)findViewById(R.id.tvDisplay);
        helper = new NotificationHelper(this);
        broadCastPowerConnected = new BroadCastPowerConnected();
        broadCastPowerDisconnected = new BroadCastPowerDisconnected();

        broadcastHelper = new BroadcastHelper(this);
    }
    private class BroadCastPowerConnected extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv.setText("FALSE");
            MakeNotification(context, "ActionPowerDisconnected", "Status", tv.getText().toString());

            Broadcast br = new Broadcast("ActionPowerDisconnected", "Action power disconnected changed to " + tv.getText() + ".", String.valueOf(Calendar.getInstance().getTime()));
            broadcastHelper.open();
            br = broadcastHelper.createBroadcast2(br);
            broadcasts = broadcastHelper.getAllBroadcasts();
            Log.d("data1", br.toString());
            Log.d("data1", broadcasts.toString());
            broadcastHelper.close();
        }
    }
    private class BroadCastPowerDisconnected extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv.setText("TRUE");
            MakeNotification(context, "ActionPowerDisconnected", "Status", tv.getText().toString());

            Broadcast br = new Broadcast("ActionPowerDisconnected", "Action power disconnected changed to " + tv.getText() + ".", String.valueOf(Calendar.getInstance().getTime()));
            broadcastHelper.open();
            br = broadcastHelper.createBroadcast2(br);
            broadcasts = broadcastHelper.getAllBroadcasts();
            Log.d("data1", br.toString());
            Log.d("data1", broadcasts.toString());
            broadcastHelper.close();
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

        broadcastHelper.open();
        SharedPreferences prefs = getSharedPreferences("broadcasts_info2", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("key1", broadcastHelper.getAllBroadcasts().toString());
        edit.commit();
        broadcastHelper.close();
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
