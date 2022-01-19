package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addToCart extends AppCompatActivity {
    TextView title_atc,price_atc,description_atc,catagory_atc,countText;
    ImageView im;
    int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        setTitle("Add to Cart");
        countText=findViewById(R.id.countText);
        title_atc=findViewById(R.id.title_atc);
        price_atc=findViewById(R.id.price_atc);
        description_atc=findViewById(R.id.description_atc);
        catagory_atc=findViewById(R.id.catagory_atc);
        im=findViewById(R.id.image_atc);
        Intent intent=getIntent();
        title_atc.setText(intent.getStringExtra("title"));
        price_atc.setText(intent.getStringExtra("price"));
        description_atc.setText(intent.getStringExtra("description"));
        catagory_atc.setText(intent.getStringExtra("catagory"));

        Glide.with(this).load(intent.getStringExtra("url")).into(im);
    }

    public void btn_minus(View view) {
        if(count<=1){
            count=1;
        }
        else{
            count=count-1;
            countText.setText(""+count);
        }
    }

    public void btn_plus(View view) {
        count=count+1;
        countText.setText(""+count);
    }

    public void addToCart(View view) {



    }
}