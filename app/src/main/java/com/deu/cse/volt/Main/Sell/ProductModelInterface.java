package com.deu.cse.volt.Main.Sell;

import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductModelInterface {
    @GET("product/model/{modelName}")
    Call<ProductModelDTO> productModel(@Path("modelName") String modelName);

}
