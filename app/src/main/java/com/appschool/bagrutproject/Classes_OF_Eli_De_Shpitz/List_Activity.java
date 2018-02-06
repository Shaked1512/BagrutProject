package com.appschool.bagrutproject.Classes_OF_Eli_De_Shpitz;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.appschool.bagrutproject.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class List_Activity extends AppCompatActivity {
    ListView listView;
    UserAdapter userAdapter;
    public static final MediaType String
            = MediaType.parse("application/string; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    void post(String url, final String string) throws IOException {
        RequestBody body = RequestBody.create(String, string);
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call call,@NonNull Response response) throws IOException {
                int i;
                int size;
                try (ResponseBody responseBody = response.body()){
                    if(!response.isSuccessful()) throw new IOException("Unexpected code"+response);
                    Headers responseheaders = response.headers();
                    for(i = 0, size = responseheaders.size(); i < size; i++){
                        Log.d("TAG", responseheaders.name(i)+": "+responseheaders.value(i));
                    }
 //                   Log.d("TAG","Mazal's DB -----> "+ responseBody.string());
                    final List<User> users = convertJsonToArrayList(responseBody.string());
                    if(users!=null){
                        for(int j = 0; j < users.size() ; j++){
                            int count = j+1;
                            String countstr = java.lang.String.valueOf(count);
                            Log.d("TAG","User Number ("+countstr+"): "+ users.get(j).toString());
                        }
                    }
                    List_Activity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            userAdapter = new UserAdapter(List_Activity.this,0,0,users);
                            listView = findViewById(R.id.lv);
                            listView.setAdapter(userAdapter);
                        }
                    });
                }
            }
        });
    }

    public List<User> convertJsonToArrayList(String strjon)
    {
        Type UserListType = new TypeToken<List<User>>(){}.getType();
        List<User> users = new Gson().fromJson(strjon, UserListType);
        return users;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);
        Log.d("TAG", "Requesting List");
        String s = getIntent().getStringExtra("List");
        if(s.equals("give list")){
            try {
                post("https://sleepy-springs-37359.herokuapp.com/getItems", s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_add || id == R.id.action_delete || id == R.id.action_profile)
            Toast.makeText(List_Activity.this, "To be continued", Toast.LENGTH_SHORT)
                    .show();
        return true;
    }
}
