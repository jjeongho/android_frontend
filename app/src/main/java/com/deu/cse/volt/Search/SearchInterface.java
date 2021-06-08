package com.deu.cse.volt.Search;

import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchInterface {

    @GET("product/search/{productName}")
    Call<SearchDTO> search(@Path("productName") String productName);
}
