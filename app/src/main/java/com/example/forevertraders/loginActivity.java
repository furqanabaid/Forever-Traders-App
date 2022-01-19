package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Reset_password(View view) {
        startActivity(new Intent(loginActivity.this,ForgotPasswordActivity.class));
    }

    public void signup(View view) {
        startActivity(new Intent(loginActivity.this,SeassionActivity.class));
    }
}