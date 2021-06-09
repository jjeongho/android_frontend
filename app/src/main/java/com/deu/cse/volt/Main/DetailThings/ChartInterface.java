package com.deu.cse.volt.Main.DetailThings;

import com.deu.cse.volt.Main.Sell.SellDTO;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChartInterface {
    @GET("transaction/statistics/{modelName}")
    Call<ChartDTO> chart(@Path("modelName") String modelName);

}
