package com.example.forevertraders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {
    RecyclerView recview;
    FirebaseAuth fauth;
    DatabaseReference myRef;
    ArrayList<myRestApiModel> list;
    myCartAdaptor adapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        fauth=FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance("https://forevertraders-f504f-default-rtdb.firebaseio.com/").getReference("Users");
        recview=findViewById(R.id.recVIew);
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list=new ArrayList<>();
        adapter=new myCartAdaptor(list,getApplicationContext());
        recview.setAdapter(adapter);
        getData();
    }
    private void getData() {
        myRef.child(fauth.getCurrentUser().getUid()).child("Carts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1:snapshot.getChildren()) {
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

            }
        });
    }

}
