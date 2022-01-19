package com.example.forevertraders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    FirebaseAuth fauth;
    EditText email,password;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fauth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email_text);
        password=findViewById(R.id.password);
        progressbar=findViewById(R.id.progressBar);

    }

    public void Reset_password(View view) {
        startActivity(new Intent(loginActivity.this, ForgotPassword.class));
    }

    public void signup(View view) {
        startActivity(new Intent(loginActivity.this, Signup.class));
    }

    public void login(View view) {
        progressbar.setVisibility(View.VISIBLE);
        String email1=email.getText().toString();
        String pass=password.getText().toString();
        fauth.signInWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful())
            {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(loginActivity.this,  "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(loginActivity.this,MainActivity.class));
            }
            else
            {
                Toast.makeText(loginActivity.this,  "Email or password is incorrect", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}