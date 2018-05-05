package com.appschool.bagrutproject.Shaked;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pc on 05/05/2018.
 */

public class ActionBootCompleted extends BroadcastReceiver {
    BroadcastHelper broadcastHelper;
    @Override
    public void onReceive(Context context, Intent intent) {
        broadcastHelper = new BroadcastHelper(context);
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context,"Boot completed!", Toast.LENGTH_LONG);
            Log.d("data1","Boot completed!");

            Broadcast br = new Broadcast("ActionBootCompleted", "Boot has just been completed.", String.valueOf(Calendar.getInstance().getTime()));
            broadcastHelper.open();
            broadcastHelper.createBroadcast2(br);
            SharedPreferences prefs = context.getSharedPreferences("broadcasts_info2", MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("key1", broadcastHelper.getAllBroadcasts().toString());
            edit.commit();
            Log.d("data2","from brHelper " + broadcastHelper.getAllBroadcasts().toString());
            Log.d("data2",prefs.getString("key1",null).toString());
            broadcastHelper.close();
        }
    }
}
