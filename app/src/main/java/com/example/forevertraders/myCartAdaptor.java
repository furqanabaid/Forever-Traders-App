package com.example.forevertraders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class myCartAdaptor extends RecyclerView.Adapter<myCartAdaptor.ViewHolder>{
    public List<myRestApiModel> list;
    public Context context;

    public myCartAdaptor(List<myRestApiModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.single_cart_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title=list.get(position).getTitle();
        String price=list.get(position).getPrice();
        String description=list.get(position).getDescription();
        String catagory=list.get(position).getCategory();
        String image=list.get(position).getImage();

        holder.restTitle.setText(title);
        holder.price.setText(price);
        holder.description.setText(description);
        holder.catagory.setText(catagory);
        Glide.with(context).load(image).into(holder.imageView);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(context,addToCart.class);
//                intent.putExtra("id",id);
//                intent.putExtra("title",title);
//                intent.putExtra("price",price);
//                intent.putExtra("description",description);
//                intent.putExtra("catagory",catagory);
//                intent.putExtra("url",image);
//                Toast.makeText(context, " "+image, Toast.LENGTH_SHORT).show();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView restTitle,price,id,description,catagory;
        ImageView imageView;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restTitle = itemView.findViewById(R.id.title_cart);
            imageView=itemView.findViewById(R.id.cartImage);
            price=itemView.findViewById(R.id.price_cart);
            id=itemView.findViewById(R.id.id_cart);
            description=itemView.findViewById(R.id.description_cart);
            catagory=itemView.findViewById(R.id.catagory_cart);
            v=itemView;
        }
    }
}
