package com.example.benchsquatdl2.retrofit;

import com.example.benchsquatdl2.model.modelApi.comment;
import com.example.benchsquatdl2.model.modelApi.trainingsdaten;
import com.example.benchsquatdl2.model.modelApi.userModelApi;
import com.example.benchsquatdl2.model.modelSpringBoot.Cart;
import com.example.benchsquatdl2.model.modelSpringBoot.Customer;
import com.example.benchsquatdl2.model.modelSpringBoot.OrderRequest;
import com.example.benchsquatdl2.model.orderResponse;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/api/trainingsdaten")
    Call<trainingsdaten> savedata(@Body trainingsdaten trainingsdaten);


    @GET("/api/getAllTraining")
    Call<List<orderResponse>> getAllProduct();



}
