package com.example.forevertraders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        holder.restTitle.setText(list.get(position).getTitle());
        holder.price.setText(list.get(position).getPrice());
        Glide.with(context).load(list.get(position).getImage()).into(holder.imageView);
        holder.Description.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView restTitle,price,Description;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restTitle = itemView.findViewById(R.id.title_cart);
            imageView=itemView.findViewById(R.id.cartImage);
            price=itemView.findViewById(R.id.price_cart);
            Description=itemView.findViewById(R.id.description_cart);
        }

    }
}
