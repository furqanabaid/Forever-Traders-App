package com.example.forevertraders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    RecyclerView onSaleNow;
    private ImageSlider imageSlider;
    myRetroFitInterface apiInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= (View) inflater.inflate(R.layout.activity_main,container,false);
        onSaleNow=view.findViewById(R.id.onSaleNow);
        imageSlider=view.findViewById(R.id.imageSlideShow);
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2016/11/22/19/08/hangers-1850082_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2017/01/18/17/14/girl-1990347_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2020/10/21/18/07/laptop-5673901_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2015/06/25/17/21/smart-watch-821557_960_720.jpg", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        //Bottom Navigation Listener
        getList();
return view;
    }

    private void getList() {
//        Snackbar.make(view,"Entered",Snackbar.LENGTH_SHORT).show();
        apiInterface=myRetrofit.getMyRetrofit().create(myRetroFitInterface.class);
        apiInterface.getList().enqueue(new Callback<List<myRestApiModel>>() {
            @Override
            public void onResponse(Call<List<myRestApiModel>> call, Response<List<myRestApiModel>> response) {
                if(response.body().size()>0){
                    List<myRestApiModel> list=response.body();
                    myRestApiAdaptor adaptor=new myRestApiAdaptor(list,getContext());
                    LinearLayoutManager linearLayoutManager=
                            new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                    onSaleNow.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    onSaleNow.setAdapter(adaptor);
//                    Snackbar.make(view,"Data received",Snackbar.LENGTH_SHORT).show();
                }
                else {
//                   Toast.makeText(MainActivity.thi, "List is empty", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<myRestApiModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Data Recieve Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
