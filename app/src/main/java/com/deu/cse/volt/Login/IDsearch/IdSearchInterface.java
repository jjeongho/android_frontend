package com.deu.cse.volt.Login.IDsearch;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IdSearchInterface {

    @GET("user/email")
    Call<IdSearchDTO> idsearch(@Query("email") String email);

//    @POST("/user")
//    Call<Map<String, String>> refreshToken(@Field("refresh_token") String refreshToken);


}
