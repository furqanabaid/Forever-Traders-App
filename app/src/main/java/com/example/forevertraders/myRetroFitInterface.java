package com.example.forevertraders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface myRetroFitInterface {
    @GET("products")
    Call<List<myRestApiModel>> getList();
    @GET("products/{id}")
    Call<List<productDetail>> getProductDetails(@Path("id") int id);
}
