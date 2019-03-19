package com.phungthanhquan.bookapp.View.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.phungthanhquan.bookapp.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private ImageView logoLogin;
    private EditText nhap_userName;
    private EditText nhap_passWord;
    private Button login;
    private TextView fotgotPass;
    private TextView register;
    private ImageView facebook;
    private ImageView google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initControls();
        eventClick();

    }

    private void eventClick() {
        login.setOnClickListener(this);
        fotgotPass.setOnClickListener(this);
        register.setOnClickListener(this);
        facebook.setOnClickListener(this);
        google.setOnClickListener(this);
    }

    private void initControls() {
        logoLogin = findViewById(R.id.logo_login);
        nhap_userName = findViewById(R.id.userName);
        nhap_passWord = findViewById(R.id.passWord);
        login = findViewById(R.id.button_Login);
        fotgotPass = findViewById(R.id.texview_fogotPass);
        register = findViewById(R.id.textview_register);
        facebook = findViewById(R.id.facebook);
        google = findViewById(R.id.google);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
         finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.button_Login:
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.texview_fogotPass:
                intent = new Intent(this,ForGotPass.class);
                startActivity(intent);
                break;
            case R.id.textview_register:
                intent = new Intent(this,Register.class);
                startActivity(intent);
                break;
            case R.id.facebook:

                break;
            case R.id.google:

                break;
        }
    }
}
