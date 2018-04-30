package com.appschool.bagrutproject.Shaked;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.appschool.bagrutproject.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ActionShutDown extends AppCompatActivity {

    ArrayList<Broadcast> broadcasts;
    BroadcastHelper broadcastHelper;
    TextView tv;
    BroadCastShutDown broadCastShutDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_shut_down);

        tv = (TextView)findViewById(R.id.tvDisplay);
        tv.setText("FALSE");

        broadCastShutDown = new BroadCastShutDown();

        broadcastHelper = new BroadcastHelper(this);
    }
    private class BroadCastShutDown extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("Shaked", "shut down");
            tv.setText("TRUE");

            Broadcast br = new Broadcast("ActionShutDown", "Action shuw down changed to " + tv.getText() + ".", String.valueOf(Calendar.getInstance().getTime()));
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
        registerReceiver(broadCastShutDown, new IntentFilter(Intent.ACTION_SHUTDOWN));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadCastShutDown);

        broadcastHelper.open();
        SharedPreferences prefs = getSharedPreferences("broadcasts_info2", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("key1", broadcastHelper.getAllBroadcasts().toString());
        edit.commit();
        broadcastHelper.close();
    }
}
