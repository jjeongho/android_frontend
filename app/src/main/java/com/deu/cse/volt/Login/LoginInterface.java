package com.deu.cse.volt.Login;


import com.deu.cse.volt.Login.UserVO;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

    @FormUrlEncoded
    @POST("oauth/token")
    Call<LoginDTO> getToken(@Field("username")String username, @Field("password")String password, @Field("grant_type")String grant_type);


//    @POST("/user")
//    Call<Map<String, String>> refreshToken(@Field("refresh_token") String refreshToken);


}
