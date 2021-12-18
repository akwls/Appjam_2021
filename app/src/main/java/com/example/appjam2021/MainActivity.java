package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    LoginActivity la = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        la = (LoginActivity)LoginActivity.loginActivity;
        if(la != null) la.finish();


    }
}
