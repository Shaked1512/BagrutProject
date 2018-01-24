package com.appschool.bagrutproject.Shaked;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.appschool.bagrutproject.R;

public class ActionShutDown extends AppCompatActivity {

    TextView tv;
    BroadCastShutDown broadCastShutDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_shut_down);

        tv = (TextView)findViewById(R.id.tvDisplay);
        tv.setText("false");

        broadCastShutDown = new BroadCastShutDown();
    }
    private class BroadCastShutDown extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("Shaked", "shut down");
            tv.setText("true");
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
    }
}
