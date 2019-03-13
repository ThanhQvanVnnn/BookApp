package com.phungthanhquan.bookapp.View.Activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.phungthanhquan.bookapp.R;

public class Login extends AppCompatActivity {
    private ImageView logoLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logoLogin = findViewById(R.id.logo_login);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
         finish();
    }

}
