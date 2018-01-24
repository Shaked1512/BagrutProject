package com.appschool.bagrutproject;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appschool.bagrutproject.Classes_OF_Eli_De_Shpitz.List_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainEli extends AppCompatActivity {
    Button btnLogin, btnRegister, btnShow, btnInfo;
    EditText etUser, etPass;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("TAG", "Failed receiving response");
            }

            @Override
            public void onResponse(@NonNull Call call,@NonNull Response response) throws IOException {
                Log.d("TAG","Server response:"+ response.body().string());

                if(response.isSuccessful()){
                    Log.d("TAG", "Logged in");
                    MainEli.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etUser.setText("");
                            etPass.setText("");
                            Toast.makeText(MainEli.this, "Logged in successfully"
                                    ,Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent intent = new Intent(MainEli.this,List_Activity.class).putExtra("List", "give list");
                    startActivity(intent);

                }
                else{
                    Log.d("TAG", "No username found :(");
                    MainEli.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etUser.setText("");
                            etPass.setText("");
                            Toast.makeText(MainEli.this, "Failed tp log in, try different user name or try registering",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    void regpost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "No server response, registration failed");
                MainEli.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainEli.this, "Failed receiving server response. Registration failed!", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d("TAG", "Successful registering.");
                    MainEli.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainEli.this, "Successful registering!", Toast.LENGTH_LONG).show();
                            etUser.setText("");
                            etPass.setText("");
                        }
                    });
                } else {
                    Log.e("TAG", "Bad response from server!");
                    MainEli.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainEli.this, "Failed to register", Toast.LENGTH_LONG).show();
                            etUser.setText("");
                            etPass.setText("");
                        }
                    });
                }
            }

        });
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eli);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLog);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUser.getText().toString().length() != 0 &&
                        etPass.getText().toString().length() != 0) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name", etUser.getText().toString());
                        jsonObject.put("password", etPass.getText().toString());
                        Log.d("TAG", "Json object created ---> " + jsonObject.toString());
                        post("https://afternoon-beach-26541.herokuapp.com/logIn", jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainEli.this, "Enter all fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnRegister = findViewById(R.id.btnReg);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUser.getText().toString().length() != 0 && etPass.getText().toString().length() != 0) {
                    JSONObject jsonreg = new JSONObject();
                    try {
                        jsonreg.put("name", etUser.getText().toString());
                        jsonreg.put("password", etPass.getText().toString());
                        Log.d("TAG", "Data awaiting server registretion ---> " + jsonreg.toString());
                        regpost("https://afternoon-beach-26541.herokuapp.com/registerItem", jsonreg.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(MainEli.this, "Can't reach server...", Toast.LENGTH_LONG).show();
            }
        });

    }
}
