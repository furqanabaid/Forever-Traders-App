package com.example.forevertraders;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class ProfileFragment extends Fragment {
    TextView email,name;
    FirebaseAuth fauth;
    DatabaseReference fobj;
    String uid;
    Button logout;
    LinearLayout l;
    FloatingActionButton editImage;
    Bitmap bitmap;
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= (View) inflater.inflate(R.layout.activity_profile_main,container,false);
        name=view.findViewById(R.id.name_1);
        email=view.findViewById(R.id.email_1);
        editImage=view.findViewById(R.id.editImage);
        logout=view.findViewById(R.id.button5);
        l=view.findViewById(R.id.l1);
        imageView=view.findViewById(R.id.imageView);
        fobj= FirebaseDatabase.getInstance().getReference().child("Users");
        fauth= FirebaseAuth.getInstance();
        uid=fauth.getCurrentUser().getUid();
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,101);
            }
        });
        fobj.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", snapshot.child(uid).child("name").getValue(String.class));
                myEdit.putString("email", snapshot.child(uid).child("email").getValue(String.class));
                myEdit.commit();

                name.setText(sharedPreferences.getString("name","NULL"));
                email.setText(sharedPreferences.getString("email","NULL"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage("Do you want to Logout ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    fauth.signOut();
                    Intent in = new Intent(new Intent(getContext(),loginActivity.class));
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getActivity().finish();
                    startActivity(in);
                }
            }).setNegativeButton("No",null);
            AlertDialog alert= builder.create();
            alert.show();

        }
    });

        MapsFragment fragment= new MapsFragment();
        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.l1,fragment);
        transaction.commit();
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode ,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101){
            try {
                InputStream inputStream=getActivity().getContentResolver()
                        .openInputStream(data.getData());
                if (bitmap!=null){
                    bitmap.recycle();
                }
                bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}