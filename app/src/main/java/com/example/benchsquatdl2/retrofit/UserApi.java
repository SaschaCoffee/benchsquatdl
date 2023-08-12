package com.example.benchsquatdl2.retrofit;

import com.example.benchsquatdl2.model.modelApi.Trainingdate;
import com.example.benchsquatdl2.model.modelApi.comment;
import com.example.benchsquatdl2.model.modelApi.trainingdto;
import com.example.benchsquatdl2.model.modelApi.trainingsdaten;
import com.example.benchsquatdl2.model.modelApi.userModelApi;
import com.example.benchsquatdl2.model.modelSpringBoot.Cart;
import com.example.benchsquatdl2.model.modelSpringBoot.Customer;
import com.example.benchsquatdl2.model.modelSpringBoot.OrderRequest;
import com.example.benchsquatdl2.model.orderResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/api/trainingsdaten")
    Call<trainingsdaten> savedata(@Body trainingsdaten trainingsdaten);

    @POST("/api/testemich")
    Call<trainingsdaten> testemich(@Body trainingsdaten trainingsdaten);

    @POST("/api/checkifexist")
    Call<trainingsdaten> checkData(@Body trainingsdaten trainingsdaten);


    @GET("/api/getAlltrainingData")
    Call<List<trainingdto>> geTrainingData();

    @POST("/api/getDatabyIdandDate")
    Call<Trainingdate> geTrainingDatabyId(@Body Trainingdate trainingdate);

    @GET("/api/getDatabyId")
    Call<List<trainingdto>> getDatabyID();



}
