package com.appschool.bagrutproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appschool.bagrutproject.Shaked.ActionPowerConnected;
import com.appschool.bagrutproject.Shaked.ActionPowerDisconnected;
import com.appschool.bagrutproject.Shaked.ActionShutDown;
import com.appschool.bagrutproject.Shaked.AirplaneMode;
import com.appschool.bagrutproject.Shaked.BatteryChanged;
import com.appschool.bagrutproject.Shaked.BatteryLow;
import com.appschool.bagrutproject.Shaked.BatteryOkay;
import com.appschool.bagrutproject.Shaked.BroadcastHelper;
import com.appschool.bagrutproject.Shaked.BroadcastList;
import com.appschool.bagrutproject.Shaked.USBConnected;

import java.util.ArrayList;

public class MainShaked extends AppCompatActivity {

    ArrayList<String> products;
    ListView lv;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shaked);

        products = new ArrayList<String>();
        products.add("ACTION_POWER_CONNECTED");
        products.add("ACTION_POWER_DISCONNECTED");
        products.add("ACTION_SHUTDOWN");
        products.add("AIRPLANE_MODE");
        products.add("BATTERY_CHANGED");
        products.add("BATTERY_LOW");
        products.add("BATTERY_OKAY");
        products.add("USB_CONNECTED");


        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, products);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(adapterView.getContext(), ActionPowerConnected.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(adapterView.getContext(), ActionPowerDisconnected.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent = new Intent(adapterView.getContext(), ActionShutDown.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(adapterView.getContext(), AirplaneMode.class);
                        startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(adapterView.getContext(), BatteryChanged.class);
                        startActivity(intent);
                        break;

                    case 5:
                        intent = new Intent(adapterView.getContext(), BatteryLow.class);
                        startActivity(intent);
                        break;

                    case 6:
                        intent = new Intent(adapterView.getContext(), BatteryOkay.class);
                        startActivity(intent);
                        break;

                    case 7:
                        intent = new Intent(adapterView.getContext(), USBConnected.class);
                        startActivity(intent);
                        break;
                }

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_shaked, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_SQLite) {
            Intent intent = new Intent(this, BroadcastList.class);
            startActivity(intent);
        }
        return true;
    }
}


