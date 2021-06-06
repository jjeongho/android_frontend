package com.deu.cse.volt.Main;


import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeInterface {

    @GET("product/products")
    Call<HomeDTO> HomeList();

//    @POST("/user")
//    Call<Map<String, String>> refreshToken(@Field("refresh_token") String refreshToken);


}
