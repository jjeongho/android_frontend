package com.deu.cse.volt.Main.Bidding.BiddingStatus;

import com.deu.cse.volt.Login.LoginDTO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BiddingTradeInterface {

    @FormUrlEncoded
    @POST("order")
    Call<TradeDTO> trade(@Field("orderType")String orderType, @Field("productGrade")String productGrade,@Field("modelName")String modelName,@Field("orderPrice") String orderPrice);

}
