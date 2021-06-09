package com.deu.cse.volt.Main.Bidding.BiddingStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.deu.cse.volt.RetrofitServiceGenerator.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.Main.Bidding.BiddingDTO;
import com.deu.cse.volt.Main.Bidding.BiddingInterface;
import com.deu.cse.volt.Main.Home.MainActivity;
import com.deu.cse.volt.Main.ProductNameTemp;
import com.deu.cse.volt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiddingSellActivity extends AppCompatActivity {
    private BiddingInterface BiddingService;
    private ProductInterface ProductService;
    private BiddingTradeInterface BiddingTradeService;
    String modelPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding_sell);
        BiddingTradeService = RetrofitBearerServiceGenerator.createService(BiddingTradeInterface.class);
        BiddingService = RetrofitBearerServiceGenerator.createService(BiddingInterface.class);
        ProductService = RetrofitBearerServiceGenerator.createService((ProductInterface.class));
        TextView submit = findViewById(R.id.bidding_sell_price_text);

        TradeVO tradeVO = loadBidding();
        loadProduct();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tradeVO.setOrderType("BUY");
                tradeVO.setProductGrade("S");
                tradeVO.setModelName(ProductNameTemp.getInstance().getProductNameTemp());
                loadOrder(new TradeVO("BUY","S",ProductNameTemp.getInstance().getProductNameTemp(),tradeVO.getOrderPrice()));
                Log.e("bidding",tradeVO.getModelName()+tradeVO.getOrderPrice()+tradeVO.getOrderType()+tradeVO.getProductGrade());

                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }



    public TradeVO loadBidding(){
        TradeVO tradeVO = new TradeVO();
        modelPrice = getIntent().getExtras().getString("Sell");//인텐트값가져오기


        BiddingService.bidding(ProductNameTemp.getInstance().getProductNameTemp()).enqueue(new Callback<BiddingDTO>() {
            TextView modelName = findViewById(R.id.bidding_sell_detail_text);
            TextView productPrice = findViewById(R.id.bidding_sell_thingspricedetail_text);
            TextView totalPrice = findViewById(R.id.bidding_sell_totalprice_text);

            @Override
            public void onResponse(Call<BiddingDTO> call, Response<BiddingDTO> response) {
                if(response != null && response.body() != null && response.isSuccessful()){
                    modelName.setText(response.body().getData().getSell().get(0).getModelname());

                    productPrice.setText(Integer.toString(Integer.parseInt(modelPrice))+" 원");
                    totalPrice.setText(Integer.toString(Integer.parseInt(modelPrice))+"  원");
                    tradeVO.setOrderPrice(Integer.parseInt(modelPrice));
                    Toast.makeText(getApplicationContext(),response.body().getResponsemessage(),Toast.LENGTH_LONG).show();

                    Log.e("bidding",response.body().getData().getSell().get(0).getModelname());

                }else{
                    Log.e("bidding",response.body().getData().getSell().get(0).getModelname());
                    Toast.makeText(getApplicationContext(),response.body().getResponsemessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<BiddingDTO> call, Throwable t) {

            }
        });
        return tradeVO;
    }


    public void loadProduct(){
        TextView ProductName = findViewById(R.id.bidding_sell_things_text);
        ImageView ProductImage = findViewById(R.id.bidding_sell_main_image);
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
        BiddingTradeService.trade(tradeVO)
                .enqueue(new Callback<TradeDTO>() {
            @Override
            public void onResponse(Call<TradeDTO> call, Response<TradeDTO> response) {
                if(response != null && response.body() != null && response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.body().getResponsemessage(),Toast.LENGTH_LONG).show();

                }else{
                    String msg = "이미 등록이 되었거나, 처리된 상품입니다.";
                    Toast.makeText(getApplicationContext(),response.body().getResponsemessage(),Toast.LENGTH_LONG).show();

                    //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TradeDTO> call, Throwable t) {

            }
        });
    }
}