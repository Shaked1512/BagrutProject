package com.appschool.bagrutproject.Shaked;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.appschool.bagrutproject.MainShaked;
import com.appschool.bagrutproject.R;

public class BatteryOkay extends AppCompatActivity {

    TextView tv;
    BroadCastLowBattery broadCastLowBattery;
    BroadCastOkayBattery broadCastOkayBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_okay);

        tv = (TextView)findViewById(R.id.tvDisplay);

        broadCastLowBattery = new BroadCastLowBattery();
        broadCastOkayBattery = new BroadCastOkayBattery();

    }

    private class BroadCastLowBattery extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv.setText("false");
            MakeNotification(context, "BatteryOkay", "Status", tv.getText().toString());
        }
    }

    private class BroadCastOkayBattery extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv.setText("true");
            MakeNotification(context, "BatteryOkay", "Status", tv.getText().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadCastLowBattery, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        registerReceiver(broadCastOkayBattery, new IntentFilter(Intent.ACTION_BATTERY_OKAY));
        EndNotification();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadCastLowBattery);
        unregisterReceiver(broadCastOkayBattery);
    }
    public void MakeNotification(Context context, String title, String ticker, String text)
    {
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
    public void EndNotification()
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }
}
