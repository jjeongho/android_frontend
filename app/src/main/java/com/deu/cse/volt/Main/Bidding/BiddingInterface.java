package com.deu.cse.volt.Main.Bidding;

import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BiddingInterface {
    @GET("order/model/{modelName}")
    Call<BiddingDTO> bidding(@Path("modelName") String modelName);
}
