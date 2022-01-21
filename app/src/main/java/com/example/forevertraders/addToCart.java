package com.example.forevertraders;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.example.forevertraders.App.CHANNEL_1_ID;

public class addToCart extends AppCompatActivity {
    TextView title_atc, price_atc, description_atc, catagory_atc, countText;
    ImageView im;
    private NotificationManagerCompat notificationManager;
    FirebaseAuth fauth;
    String image;
    float id_product;
    DatabaseReference myRef;
    Button add_to_cart_btn;
    int count = 1;
    Toolbar toolbar;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        //setTitle("Add to Cart");
        add_to_cart_btn=findViewById(R.id.button5);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add to cart");
        actionBar.setDisplayHomeAsUpEnabled(true);

        countText = findViewById(R.id.countText);
        fauth = FirebaseAuth.getInstance();
        title_atc = findViewById(R.id.title_atc);
        price_atc = findViewById(R.id.price_atc);
        description_atc = findViewById(R.id.description_atc);
        catagory_atc = findViewById(R.id.catagory_atc);
        im = findViewById(R.id.image_atc);
        notificationManager = NotificationManagerCompat.from(this);
        Intent intent = getIntent();
        id_product = intent.getFloatExtra("id", 0);
        title_atc.setText(intent.getStringExtra("title"));
        price_atc.setText(intent.getStringExtra("price"));
        description_atc.setText(intent.getStringExtra("description"));
        catagory_atc.setText(intent.getStringExtra("catagory"));
        image = intent.getStringExtra("url");
        Glide.with(this).load(image).into(im);
    }

    public void btn_minus(View view) {
        if (count <= 1) {
            count = 1;
        } else {
            count = count - 1;
            countText.setText("" + count);
        }
    }

    public void btn_plus(View view) {
        count = count + 1;
        countText.setText("" + count);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addToCart(View view) {
        String instance = "https://forevertraders-f504f-default-rtdb.firebaseio.com/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(instance);
        myRef = database.getReference("Users");
        myRestApiModel model = new myRestApiModel();
        String title_fb = title_atc.getText().toString();
        model.setTitle(title_fb);
        model.setImage(image);
        model.setCategory(catagory_atc.getText().toString());
        model.setPrice(price_atc.getText().toString());
        model.setDescription(description_atc.getText().toString());
        model.setQuantity(count);
        id = myRef.push().getKey();
        model.setId(id_product);
        String title = title_fb;
        String message = "New item added to your cart!";
        myRef.child(fauth.getCurrentUser().getUid()).child("Carts").child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
//                    NotificationCompat.Builder builder =
//                            new NotificationCompat.Builder(addToCart.this)
//                                    .setAutoCancel(true)
//
//                                    .setSmallIcon(R.drawable.ic_one)
//                                    .setContentTitle("Notifications Example")
//                                    .setContentIntent(null)
//                                    .setContentText("This is a test notification")
//                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        CharSequence name = "HEllo";
//                        String description = "i love you";
//                        int importance = NotificationManager.IMPORTANCE_DEFAULT;
//                        NotificationChannel channel = new NotificationChannel(CHANNEL_1_ID, name, importance);
//                        channel.setDescription(description);
//                        // Register the channel with the system; you can't change the importance
//                        // or other notification behaviors after this
//                        NotificationManager notificationManager = getSystemService(NotificationManager.class);
//                        notificationManager.createNotificationChannel(channel);
//
//                    }
//                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(addToCart.this);
//                    notificationManager.notify(1, builder.build());
                    Toast.makeText(getApplicationContext(), "Added to Cart Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed To add", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //Notification

    }
}