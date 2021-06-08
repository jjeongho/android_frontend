package com.deu.cse.volt.Myinfo;

import com.deu.cse.volt.Login.IDsearch.IdSearchDTO;
import com.deu.cse.volt.Main.Home.HomeDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyinfoInterface {
    @GET("user/id")
    Call<UserIdDTO> myInfo(@Query("username") String username);

}
