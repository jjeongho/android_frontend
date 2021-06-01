package com.deu.cse.volt.Login;


import com.deu.cse.volt.Login.UserVO;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignUpInterface {

    @POST("user")
    Call<SignUpDTO> getToken(@Body SignUpBodyRequest signUpBodyRequest);


//    @POST("/user")
//    Call<Map<String, String>> refreshToken(@Field("refresh_token") String refreshToken);


}
