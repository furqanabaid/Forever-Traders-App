package com.example.forevertraders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    RecyclerView onSaleNow;

    private ImageSlider imageSlider;
    myRetroFitInterface apiInterface;
    public ProgressBar progressbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= (View) inflater.inflate(R.layout.activity_main,container,false);
        onSaleNow=view.findViewById(R.id.onSaleNow);
        progressbar=view.findViewById(R.id.progressBar1);
        imageSlider=view.findViewById(R.id.imageSlideShow);
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2016/11/22/19/08/hangers-1850082_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2017/01/18/17/14/girl-1990347_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2020/10/21/18/07/laptop-5673901_960_720.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2015/06/25/17/21/smart-watch-821557_960_720.jpg", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        getList();
        return view;
    }

    private void getList() {

        getApiData getApiData=new getApiData();
        getApiData.execute();

    }
    class getApiData extends AsyncTask<Void,Void, Void> {
//        ProgressBar progressbar;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressbar.setVisibility(View.VISIBLE);


        }
        @Override
        protected Void doInBackground(Void... voids) {
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
                    }
                    else {
                        Toast.makeText(getContext(), "List is empty", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<myRestApiModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Data Recieve Failed.", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressbar.setVisibility(View.GONE);
        }
    }

}
