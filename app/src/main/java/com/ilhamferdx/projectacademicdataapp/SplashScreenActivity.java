package com.ilhamferdx.projectacademicdataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        int SPLASH_SCREEN_TIME = 3000; //3 detik

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(com.ilhamferdx.projectacademicdataapp.SplashScreenActivity.this, LoginActivity.class);
                com.ilhamferdx.projectacademicdataapp.SplashScreenActivity.this.startActivity(intent);
                com.ilhamferdx.projectacademicdataapp.SplashScreenActivity.this.finish();
            }
        }, SPLASH_SCREEN_TIME);
    }
}