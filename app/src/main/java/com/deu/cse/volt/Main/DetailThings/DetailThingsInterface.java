package com.deu.cse.volt.Main.DetailThings;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailThingsInterface {

    @GET("product/prd/{productName}")
    Call<DetailThingsDTO> detailThings(@Path("productName") String productName);

//    @POST("/user")
//    Call<Map<String, String>> refreshToken(@Field("refresh_token") String refreshToken);


}
