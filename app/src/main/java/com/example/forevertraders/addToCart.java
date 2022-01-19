package com.example.forevertraders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addToCart extends AppCompatActivity {
    TextView title_atc,price_atc,description_atc,catagory_atc,countText;
    ImageView im;
    FirebaseAuth fauth;
    String image;
    float id_product;
    DatabaseReference myRef;
    int count=1;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        setTitle("Add to Cart");
        countText=findViewById(R.id.countText);
        fauth=FirebaseAuth.getInstance();
        title_atc=findViewById(R.id.title_atc);
        price_atc=findViewById(R.id.price_atc);
        description_atc=findViewById(R.id.description_atc);
        catagory_atc=findViewById(R.id.catagory_atc);
        im=findViewById(R.id.image_atc);

        Intent intent=getIntent();
        id_product=intent.getFloatExtra("id",0);
        title_atc.setText(intent.getStringExtra("title"));
        price_atc.setText(intent.getStringExtra("price"));
        description_atc.setText(intent.getStringExtra("description"));
        catagory_atc.setText(intent.getStringExtra("catagory"));
        image=intent.getStringExtra("url");
        Glide.with(this).load(image).into(im);
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
        String instance="https://forevertraders-f504f-default-rtdb.firebaseio.com/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(instance);
        myRef = database.getReference("Users");
        myRestApiModel model=new myRestApiModel();
        model.setTitle(title_atc.getText().toString());
        model.setImage(image);
        model.setCategory(catagory_atc.getText().toString());
        model.setPrice(price_atc.getText().toString());
        model.setDescription(description_atc.getText().toString());
        model.setQuantity(count);
        id=myRef.push().getKey();
        model.setId(id_product);
        myRef.child(fauth.getCurrentUser().getUid()).child("Carts").child(id).setValue(model);
    }
}