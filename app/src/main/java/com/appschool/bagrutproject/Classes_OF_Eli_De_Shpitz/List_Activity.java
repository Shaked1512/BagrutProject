package com.appschool.bagrutproject.Classes_OF_Eli_De_Shpitz;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.appschool.bagrutproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
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
    public static final MediaType String
            = MediaType.parse("application/string; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    void post(String url, String string) throws IOException {
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
                    Log.d("TAG","Mazal's DB -----> "+ responseBody.string());
                    String string = responseBody.toString();
                    List<User> users = convertJsonToArrayList(string);
                    if(users!=null)
                        Log.d("TAG", "LIST: "+users.toString());
                }
            }
        });
    }

    public List<User> convertJsonToArrayList(String strjon)
    {
        Log.d("TAG", "a");
        Type UserListType = new TypeToken<List<User>>(){}.getType();
        Log.d("TAG", "b");
        List<User> users = new Gson().fromJson(strjon, UserListType);
        Log.d("TAG", "LIST: "+users.toString());
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
}
