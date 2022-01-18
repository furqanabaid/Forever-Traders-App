package com.example.forevertraders;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myRetrofit {
    public static Retrofit retrofit;
    public static String uRL="https://fakestoreapi.com/";
    public static Retrofit getMyRetrofit(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(uRL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

