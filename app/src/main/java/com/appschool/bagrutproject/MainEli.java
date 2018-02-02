package com.appschool.bagrutproject;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
    Dialog d, log_d, reg_d;
    ProgressDialog progressDialog;
    Button btnLogin, btnRegister, btnUnderstood, btnlog, btnreg;
    EditText pass_field, user_field;
    EditText reg_pass, reguser, regname, regLname;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    void post(String url, String json) throws IOException {
        Log.d("TAG", "Login called");
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
                MainEli.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnlog.setClickable(true);
                        progressDialog.dismiss();
                        Toast.makeText(MainEli.this, "Failed receiving response from server", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call,@NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.d("TAG", "Logged in");
                    MainEli.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            btnlog.setClickable(true);
                            user_field.setText("");
                            pass_field.setText("");
                            Toast.makeText(MainEli.this, "Logged in successfully"
                                    ,Toast.LENGTH_LONG).show();
                            log_d.dismiss();
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
                            progressDialog.dismiss();
                            btnlog.setClickable(true);
                            user_field.setText("");
                            pass_field.setText("");
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
                        btnreg.setClickable(true);
                        progressDialog.dismiss();
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
                            btnreg.setClickable(true);
                            progressDialog.dismiss();
                            Toast.makeText(MainEli.this, "Successful registering!", Toast.LENGTH_SHORT).show();
                            reguser.setText("");
                            reg_pass.setText("");
                            regname.setText("");
                            regLname.setText("");
                            reg_d.dismiss();
                        }
                    });
                } else {
                    Log.e("TAG", "Bad response from server!");
                    MainEli.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnreg.setClickable(true);
                            progressDialog.dismiss();
                            Toast.makeText(MainEli.this, "Failed to register", Toast.LENGTH_LONG).show();
                            reguser.setText("");
                            reg_pass.setText("");
                            regname.setText("");
                            regLname.setText("");
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
        btnLogin = findViewById(R.id.btnLog);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createLoginDialog();
            }
        });
        btnRegister = findViewById(R.id.btnReg);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRegisterDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_info){
            createInfoDialog();
        }
        return true;
    }

    public void createInfoDialog()
    {
        d= new Dialog(MainEli.this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.info_layout);
        d.setCancelable(false);
        btnUnderstood=d.findViewById(R.id.btnUnderstood);
        btnUnderstood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
        d.show();
    }

    public void createLoginDialog(){
        log_d = new Dialog(MainEli.this);
        log_d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        log_d.setCancelable(true);
        log_d.setContentView(R.layout.login_layout);
        pass_field = log_d.findViewById(R.id.pass_field);
        user_field = log_d.findViewById(R.id.user_field);
        btnlog = log_d.findViewById(R.id.btn_login);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnlog.setClickable(false);
                showProgress();
                if(pass_field.getText().length() != 0 && user_field.getText().length()!=0){
                    JSONObject jsonlog = new JSONObject();
                    try{
                        jsonlog.put("name", user_field.getText().toString());
                        jsonlog.put("password", pass_field.getText().toString());
                        Log.d("TAG", "Json object created ---> " + jsonlog.toString());

                        post("https://sleepy-springs-37359.herokuapp.com/", jsonlog.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                       e.printStackTrace();
                    }
                } else {
                    btnlog.setClickable(true);
                    Toast.makeText(MainEli.this, "Enter all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        log_d.show();
    }

    public void createRegisterDialog(){
        reg_d = new Dialog(MainEli.this);
        reg_d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        reg_d.setCancelable(true);
        reg_d.setContentView(R.layout.register_layout);
        reguser = reg_d.findViewById(R.id.register_user);
        reg_pass = reg_d.findViewById(R.id.register_pass);
        regname = reg_d.findViewById(R.id.register_name);
        regLname = reg_d.findViewById(R.id.register_last);
        btnreg = reg_d.findViewById(R.id.btn_reg);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reg_pass.getText().toString().length() != 0 && reguser.getText().toString().length() != 0
                        && regname.getText().length() != 0 && regLname.getText().length() != 0){
                    btnreg.setClickable(false);
                    showProgress();
                    JSONObject jsonreg = new JSONObject();
                    try {
                        jsonreg.put("name", reguser.getText().toString());
                        jsonreg.put("password", reg_pass.getText().toString());
                        jsonreg.put("first-name", regname.getText().toString());
                        jsonreg.put("last-name", regLname.getText().toString());
                        Log.d("TAG", "Data awaiting server registration ---> " + jsonreg.toString());
                        regpost("https://sleepy-springs-37359.herokuapp.com/registerItem", jsonreg.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    btnreg.setClickable(true);
                    Toast.makeText(MainEli.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        reg_d.show();
    }

    public void showProgress(){
        progressDialog = ProgressDialog.show(MainEli.this,"Loading","Please wait!",true);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
}
