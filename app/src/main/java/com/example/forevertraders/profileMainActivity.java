package com.example.forevertraders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileMainActivity extends AppCompatActivity {
TextView email,name;
FirebaseAuth fauth;
DatabaseReference fobj;
String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        name=findViewById(R.id.name_1);
        email=findViewById(R.id.email_1);

        fobj=FirebaseDatabase.getInstance().getReference().child("Users");
        fauth= FirebaseAuth.getInstance();
        uid=fauth.getCurrentUser().getUid();
        fobj.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    name.setText(snapshot.child(uid).child("name").getValue(String.class));
                   email.setText(snapshot.child(uid).child("email").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void logout(View view) {
        fauth.signOut();
        Intent in = new Intent(new Intent(this,loginActivity.class));
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(in);

    }
}