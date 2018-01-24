package com.appschool.bagrutproject.Classes_OF_Eli_De_Shpitz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appschool.bagrutproject.R;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class List_Activity extends AppCompatActivity {
    public static final MediaType String
            = MediaType.parse("application/string; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

/*    void post(String url, String string) throws IOException {
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
                    String str1 = responseBody.string();
                    ArrayList<User> users = convertJsonToArrayList(str1);
//                    if(users!=null)
//                    Log.d("TAG", users.toString());

                }
            }
        });
    }

    public ArrayList<User> convertJsonToArrayList(String strjon)
    {
        Log.d("TAG", "a");

        ArrayList<User>arrayList=new ArrayList<User>();
        JSONArray jArray = null;
        try {
            Log.d("TAG", "b");
            jArray = new JSONArray(strjon);
            Log.d("TAG", "c");


            for(int i=0;i<jArray.length();i++)
            {
                Log.d("TAG", "d" + i);

                Log.d("asaf","try json"+i);
                JSONObject json_data = jArray.getJSONObject(i);
                String name = json_data.getString("name");
                String password = json_data.getString("password");
                //String userId = json_data.getString("12");
                User user= new User(name, password,"12");
                arrayList.add(user);

            }
            return arrayList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);
        /*String s = getIntent().getStringExtra("List");
        if(s.equals("give list")){
            try {
                post("https://afternoon-beach-26541.herokuapp.com/getItems", s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/


    }
}
