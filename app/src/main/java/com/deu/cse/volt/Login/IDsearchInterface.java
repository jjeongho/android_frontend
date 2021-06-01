package com.deu.cse.volt.Login;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDsearchInterface {

    @GET("user/email")
    Call<IDsearchDTO> idsearch(@Query("email") String email);

//    @POST("/user")
//    Call<Map<String, String>> refreshToken(@Field("refresh_token") String refreshToken);


}
