package com.example.forevertraders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface myRetroFitInterface {
    @GET("products")
    Call<List<myRestApiModel>> getList();
//    @GET("posts/1/comments")
//    Call<List<myCommetsModel>> getComment();
}
