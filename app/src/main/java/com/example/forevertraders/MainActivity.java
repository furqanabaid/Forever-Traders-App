package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView onSaleNow,newArrival,shopWomen,shopMen,shopKids;
    myRetroFitInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Binding
        onSaleNow=findViewById(R.id.onSaleNow);
//        newArrival=findViewById(R.id.newArrival);
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
                    LinearLayoutManager linearLayoutManager=
                            new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,true);
                    onSaleNow.setLayoutManager(linearLayoutManager);
                    onSaleNow.setAdapter(adaptor);
                    Toast.makeText(MainActivity.this, "Data Recieved.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<myRestApiModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}