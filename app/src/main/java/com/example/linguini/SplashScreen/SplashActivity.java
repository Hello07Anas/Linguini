package com.example.linguini.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.linguini.HomeScreen.view.HomeActivity;
import com.example.linguini.MainActivity;
import com.example.linguini.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    private static final String PREF_NAME = "AuthState";
    private static final String IS_LOGGED_IN = "IsLoggedIn";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.splashanimation).into(imageView);

        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean(IS_LOGGED_IN, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (isLoggedIn) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }



                //Intent i = new Intent(SplashActivity.this, MainActivity.class);
                // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                // TODO Bougs here "not all time will go to MainActivity"
                // check if the user logged in go To Home Screen if not go to Login Screen
                // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                //startActivity(i);
                //finish();
            }
        }, SPLASH_TIME_OUT);
    }
}