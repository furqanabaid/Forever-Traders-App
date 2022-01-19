package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    private static int Splash_Time_out=3000;
    ProgressBar p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        p=findViewById(R.id.progressBar3);
        p.setProgress(View.VISIBLE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(SplashActivity.this, loginActivity.class);
                startActivity(home);
                finish();
                p.setProgress(View.GONE);
            }
        }, Splash_Time_out);
    }
}