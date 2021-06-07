package com.deu.cse.volt.Main.Sell;

import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;
import com.deu.cse.volt.Main.Home.HomeDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SellStartInterface {

    @GET("product/model/{modelName}")
    Call<SellDTO> sell(@Path("modelName") String modelName);

}
