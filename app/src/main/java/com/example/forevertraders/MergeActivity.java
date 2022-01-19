package com.example.forevertraders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MergeActivity extends AppCompatActivity {
    public static BottomNavigationView navView;
    ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
        navView = findViewById(R.id.bottom_navigation);
        ProfileFragment profileFragment=new ProfileFragment();
        HomeFragment homeFragment=new HomeFragment();
        CartFragment cartFragment =new CartFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment,homeFragment);
        transaction.commit();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.Home:
                        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment,homeFragment);
                        transaction.commit();
                        return true;

                    case R.id.Cart:
                        FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
                        transaction1.replace(R.id.fragment,cartFragment);
                        transaction1.commit();
                        return true;

                    case R.id.Profile:
                        FragmentTransaction transaction2=getSupportFragmentManager().beginTransaction();
                        transaction2.replace(R.id.fragment,profileFragment);
                        transaction2.commit();
                        return true;
                }

                return false;

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(exampleBroadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(exampleBroadcastReceiver);
    }
}