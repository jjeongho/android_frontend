package com.deu.cse.volt.Main.Home;


import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeInterface {

    @GET("product/products")
    Call<HomeDTO> HomeList();

//    @POST("/user")
//    Call<Map<String, String>> refreshToken(@Field("refresh_token") String refreshToken);


}
