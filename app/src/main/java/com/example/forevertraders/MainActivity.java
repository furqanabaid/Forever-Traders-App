package com.example.forevertraders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView onSaleNow;
    private ImageSlider imageSlider;
    myRetroFitInterface apiInterface;
    public static BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Binding
        onSaleNow=findViewById(R.id.onSaleNow);
        imageSlider=findViewById(R.id.imageSlideShow);
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2016/11/22/19/08/hangers-1850082_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2017/01/18/17/14/girl-1990347_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2020/10/21/18/07/laptop-5673901_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2015/06/25/17/21/smart-watch-821557_960_720.jpg", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        //Bottom Navigation Listener
        navView = findViewById(R.id.bottom_navigation);
        Intent intent=new Intent(MainActivity.this,Cart.class);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.Home:

                        return true;

                    case R.id.Cart:
                        startActivity(intent);
                        return true;

                    case R.id.Profile:
                        startActivity(new Intent(MainActivity.this,profileMainActivity.class));
                        return true;
                }

                return false;

            }
        });



        getList();
    }

    private void getList() {
        apiInterface=myRetrofit.getMyRetrofit().create(myRetroFitInterface.class);
        apiInterface.getList().enqueue(new Callback<List<myRestApiModel>>() {
            @Override
            public void onResponse(Call<List<myRestApiModel>> call, Response<List<myRestApiModel>> response) {
                if(response.body().size()>0){
                    List<myRestApiModel> list=response.body();
                    myRestApiAdaptor adaptor=new myRestApiAdaptor(list,getApplicationContext());
//                    LinearLayoutManager linearLayoutManager=
//                            new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,true);
                    onSaleNow.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    onSaleNow.setAdapter(adaptor);
                    Log.d("TAG","Data Recieved");
                }
                else {
                    Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<myRestApiModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Data Recieve Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}