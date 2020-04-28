package com.example.palaver.mainWorkflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.palaver.R;
import com.example.palaver.ui.login.LoginActivity;

public class SplashScreenView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenView.this, LoginActivity.class);
                startActivity(i);

                finish();
            }
        }, 3000);
    }
}
