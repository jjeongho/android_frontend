package com.deu.cse.volt.Main.DetailThings;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AiInterface {

    @GET("volt/ai")
    Call<AiDTO> ai(@Query("product_name") String product_name);

}
