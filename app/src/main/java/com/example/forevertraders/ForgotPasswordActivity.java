package com.example.forevertraders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    ConstraintLayout layout;
    Button reset;
    FirebaseAuth fauth;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);;
        fauth = FirebaseAuth.getInstance();
        reset=findViewById(R.id.button2);
        email=findViewById(R.id.email_reset);

    }

    public void reset(View view) {
        String email1=email.getText().toString();
        fauth.sendPasswordResetEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Reset Link has been sent to your Email Address.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Failed to send link.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    startActivity(new Intent(ForgotPasswordActivity.this,ViewEmailActivity.class));
    }
}