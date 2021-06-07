package com.deu.cse.volt.Main.Bidding.BiddingStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.deu.cse.volt.Login.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.Main.Bidding.BiddingDTO;
import com.deu.cse.volt.Main.Bidding.BiddingInterface;
import com.deu.cse.volt.Main.DetailThingsActivity;
import com.deu.cse.volt.Main.ProductNameTemp;
import com.deu.cse.volt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiddingPurchaseActivity extends AppCompatActivity {
    private BiddingInterface BiddingService;
    private ProductInterface ProductService;
    private BiddingTradeInterface BiddingTradeService;
    String modelPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding_purchase);
        BiddingService = RetrofitBearerServiceGenerator.createService(BiddingInterface.class);
        ProductService = RetrofitBearerServiceGenerator.createService((ProductInterface.class));
        BiddingTradeService = RetrofitBearerServiceGenerator.createService(BiddingTradeInterface.class);
        ImageView submit = findViewById(R.id.bidding_purchase_purchase_btn);
        TradeVO tradeVO = loadBidding();


        loadProduct();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tradeVO.setOrderType("SELL");
                tradeVO.setProductGrade("S");
                tradeVO.setModelName(ProductNameTemp.getInstance().getProductNameTemp());
                loadOrder(new TradeVO("SELL","S",ProductNameTemp.getInstance().getProductNameTemp(),tradeVO.getOrderPrice()));
                Log.e("bidding",tradeVO.getModelName()+tradeVO.getOrderPrice()+tradeVO.getOrderType()+tradeVO.getProductGrade());
            }
        });

    }


    public TradeVO loadBidding(){
        TradeVO tradeVO = new TradeVO();
        modelPrice = getIntent().getExtras().getString("Buy");//인텐트값가져오기

        BiddingService.bidding(ProductNameTemp.getInstance().getProductNameTemp()).enqueue(new Callback<BiddingDTO>() {
            TextView modelName = findViewById(R.id.bidding_purchase_detail_text);
            TextView productPrice = findViewById(R.id.bidding_purchase_thingspricedetail_text);
            TextView totalPrice = findViewById(R.id.bidding_purchase_totalprice_text);
            @Override
            public void onResponse(Call<BiddingDTO> call, Response<BiddingDTO> response) {
                if(response != null && response.body() != null && response.isSuccessful()){
                    modelName.setText(response.body().getData().getBuy().get(0).getModelname());
                    productPrice.setText(Integer.toString(Integer.parseInt(modelPrice))+" 원");
                    totalPrice.setText(Integer.toString(Integer.parseInt(modelPrice))+"  원");
                    tradeVO.setOrderPrice(Integer.toString(Integer.parseInt(modelPrice)));
                    Log.e("bidding",response.body().getData().getBuy().get(0).getModelname());
                }else{
                    Log.e("bidding",response.body().getData().getBuy().get(0).getModelname());
                }
            }

            @Override
            public void onFailure(Call<BiddingDTO> call, Throwable t) {

            }
        });
        return tradeVO;
    }

    public void loadProduct(){
        TextView ProductName = findViewById(R.id.bidding_purchase_things_text);
        ImageView ProductImage = findViewById(R.id.bidding_purchase_main_image);
        ProductService.product(ProductNameTemp.getInstance().getProductNameTemp()).enqueue(new Callback<ProductDTO>() {
            @Override
            public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response) {
                if(response != null && response.body() != null && response.isSuccessful()) {
                    ProductName.setText(response.body().getData().getResult().getProductname());
                    Glide.with(getApplicationContext()).load(response.body().getData().getResult().getProductpicture()).into(ProductImage);

                }else{

                }
            }

            @Override
            public void onFailure(Call<ProductDTO> call, Throwable t) {

            }
        });
    }

    public void loadOrder(TradeVO tradeVO){
        BiddingTradeService.trade(tradeVO.getOrderType(),tradeVO.getProductGrade(),tradeVO.getModelName(),tradeVO.getOrderPrice())
                .enqueue(new Callback<TradeDTO>() {
                    @Override
                    public void onResponse(Call<TradeDTO> call, Response<TradeDTO> response) {
                        if(response != null && response.body() != null && response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),response.body().getResponsemessage(),Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), DetailThingsActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            String msg = "이미 등록이 되었거나, 처리된 상품입니다.";
                            //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<TradeDTO> call, Throwable t) {

                    }
                });
    }
}