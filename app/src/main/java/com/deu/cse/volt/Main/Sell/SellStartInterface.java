package com.deu.cse.volt.Main.Sell;

import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;
import com.deu.cse.volt.Main.Home.HomeDTO;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SellStartInterface {

    @GET("transaction/statistics/{modelName}/{localDate}")
    Call<SellDTO> sell(@Path("modelName") String modelName, @Path("localDate")LocalDate localdate);

}
