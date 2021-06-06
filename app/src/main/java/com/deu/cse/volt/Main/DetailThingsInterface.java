package com.deu.cse.volt.Main;


import com.deu.cse.volt.Login.IDsearch.IdSearchDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DetailThingsInterface {

    @GET("product/prd/{productName}")
    Call<DetailThingsDTO> detailThings(@Path("productName") String productName);

//    @POST("/user")
//    Call<Map<String, String>> refreshToken(@Field("refresh_token") String refreshToken);


}
