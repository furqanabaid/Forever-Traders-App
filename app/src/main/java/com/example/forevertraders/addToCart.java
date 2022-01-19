package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class addToCart extends AppCompatActivity {
    TextView title_atc,price_atc,description_atc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        setTitle("Add to Cart");
        title_atc=findViewById(R.id.title_atc);
        price_atc=findViewById(R.id.price_atc);
        description_atc=findViewById(R.id.description_atc);
        Intent intent=getIntent();
        String title= String.valueOf(intent.getIntExtra("id",0));
        Toast.makeText(addToCart.this, " "+title, Toast.LENGTH_SHORT).show();
        title_atc.setText(title);
    }
}