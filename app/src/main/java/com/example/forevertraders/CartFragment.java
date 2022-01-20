package com.example.forevertraders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    RecyclerView recview;
    FirebaseAuth fauth;
    DatabaseReference myRef;
    ArrayList<myRestApiModel> list;
    myCartAdaptor adapter;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.activity_cart, container, false);
        fauth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance("https://forevertraders-f504f-default-rtdb.firebaseio.com/").getReference("Users");
        recview = view.findViewById(R.id.recVIew);
        recview.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        list = new ArrayList<>();
        adapter = new myCartAdaptor(list, getContext().getApplicationContext());
        recview.setAdapter(adapter);
        getData();
        return view;
    }
        private void getData () {
            myRef.child(fauth.getCurrentUser().getUid()).child("Carts").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        myRestApiModel dataModel = new myRestApiModel();
                        dataModel.setTitle(snapshot1.child("title").getValue(String.class));
                        dataModel.setDescription(snapshot1.child("description").getValue(String.class));
                        dataModel.setPrice(snapshot1.child("price").getValue(String.class));
                        dataModel.setCategory(snapshot1.child("category").getValue(String.class));
                        dataModel.setImage(snapshot1.child("image").getValue(String.class));
                        list.add(dataModel);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), " Canceleed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }