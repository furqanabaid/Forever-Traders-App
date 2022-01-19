package com.example.forevertraders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
    TextView email,name;
    FirebaseAuth fauth;
    DatabaseReference fobj;
    String uid;
    Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= (View) inflater.inflate(R.layout.activity_profile_main,container,false);
        name=view.findViewById(R.id.name_1);
        email=view.findViewById(R.id.email_1);
        logout=view.findViewById(R.id.button5);
        fobj= FirebaseDatabase.getInstance().getReference().child("Users");
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

    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                fauth.signOut();
                Intent in = new Intent(new Intent(getContext(),loginActivity.class));
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(in);
        }
    });
        return view;
    }

}