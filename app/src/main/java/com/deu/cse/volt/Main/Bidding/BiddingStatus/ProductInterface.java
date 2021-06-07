package com.deu.cse.volt.Main.Bidding.BiddingStatus;

import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductInterface {

    @GET("product/model/{modelName}")
    Call<ProductDTO> product(@Path("modelName") String modelName);

}
