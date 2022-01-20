package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class SpinAndWin extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    Intent serviceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_and_win);
        setTitle("Spin and Win");
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        lottieAnimationView=findViewById(R.id.spinANdWin_animation);
        serviceIntent=new Intent(getApplicationContext(),MyService.class);
    }

    public void startService(View view) {
        lottieAnimationView.playAnimation();
        startService(serviceIntent);
    }

    public void stopService(View view) {
        lottieAnimationView.pauseAnimation();
        stopService(serviceIntent);
    }
}