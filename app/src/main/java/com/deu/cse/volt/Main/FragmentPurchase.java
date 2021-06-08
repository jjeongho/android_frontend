package com.deu.cse.volt.Main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.deu.cse.volt.Login.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.Main.Bidding.BiddingStatus.BiddingTradeInterface;
import com.deu.cse.volt.Main.Bidding.BiddingStatus.TradeDTO;
import com.deu.cse.volt.Main.Bidding.BiddingStatus.TradeVO;
import com.deu.cse.volt.Main.Home.MainActivity;
import com.deu.cse.volt.Main.Sell.ProductModelDTO;
import com.deu.cse.volt.Main.Sell.ProductModelInterface;
import com.deu.cse.volt.Main.Sell.SellDTO;
import com.deu.cse.volt.Main.Sell.SellStartInterface;
import com.deu.cse.volt.R;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPurchase extends Fragment {
    private SellStartInterface SellStartService;
    private ProductModelInterface productModelService;
    private BiddingTradeInterface BiddingTradeService;
    SeekBar seekBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.purchase_fragment, container, false);
        SellStartService = RetrofitBearerServiceGenerator.createService(SellStartInterface.class);
        productModelService = RetrofitBearerServiceGenerator.createService(ProductModelInterface.class);
        BiddingTradeService = RetrofitBearerServiceGenerator.createService(BiddingTradeInterface.class);
        seekBar =  (SeekBar)rootView.findViewById(R.id.purchase_seekbar);
        TextView submit = rootView.findViewById(R.id.bidding_purchase_price_text);
        TextView hopeBuyPrice = (TextView)rootView.findViewById(R.id.purchase_hopebuyprice_text);
        TextView PaymentText = (TextView)rootView.findViewById(R.id.purchase_total_text);
        TradeVO tradeVO = loadStartSell(rootView);
        loadImage(rootView);

//        if(getArguments() != null) { // bearertoken 받아오기
//            String token = getArguments().getString("bearertoken");
//        }


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override//볼륨은 조절해도되지만 화면조절은 버벅될 우려가 있음
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                hopeBuyPrice.setText(+seekBar.getProgress() + "  원");
                PaymentText.setText(+seekBar.getProgress() + "  원");
                submit.setText(+seekBar.getProgress() + "  원");


            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //display.setText(seekBar.getProgress() + "");
                //드래그가 끝난뒤 표시됨
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("FRAGMENTSELL",String.valueOf(seekBar.getProgress()));

                loadOrder(new TradeVO("BUY","S",ProductNameTemp.getInstance().getProductNameTemp(),seekBar.getProgress()));
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

//        if(getArguments() != null) { // bearertoken 받아오기
//            String token = getArguments().getString("bearertoken");
//        }


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public TradeVO loadStartSell(ViewGroup rootView){
        TradeVO tradeVO =new TradeVO();
        TextView hopeBuyPrice = (TextView)rootView.findViewById(R.id.purchase_hopebuyprice_text);
        TextView PaymentText = (TextView)rootView.findViewById(R.id.purchase_modeltuff_text);

        TextView sellImageModelText = (TextView)rootView.findViewById(R.id.purchase_modeltuff_text);
        TextView submit = (TextView)rootView.findViewById(R.id.bidding_purchase_price_text);
        TextView minPrice = (TextView)rootView.findViewById(R.id.purchase_smallestprice_text);
        TextView maxPrice = (TextView)rootView.findViewById(R.id.pruchase_biggestprice_text);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SellStartService.sell(ProductNameTemp.getInstance().getProductNameTemp(), LocalDate.now().minusDays(5)).enqueue(new Callback<SellDTO>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<SellDTO> call, Response<SellDTO> response) {
                    if (response.isSuccessful()) {
                        Log.d("Next!",response.body().getResponsemessage().toString());

                        sellImageModelText.setText(response.body().getData().getResult().getModelname());
                        hopeBuyPrice.setText(Integer.toString(response.body().getData().getResult().getAvgprice())+"  원");
                        PaymentText.setText(Integer.toString(response.body().getData().getResult().getAvgprice())+"  원");
                        submit.setText(Integer.toString(response.body().getData().getResult().getAvgprice())+"  원 결제하기");
                        maxPrice.setText(Integer.toString((int) (response.body().getData().getResult().getHighestprice()+ (response.body().getData().getResult().getHighestprice()*0.1))));
                        minPrice.setText(Integer.toString((int) (response.body().getData().getResult().getLowestprice()-(response.body().getData().getResult().getHighestprice()*0.1))));

                        seekBar.setMax((int) (response.body().getData().getResult().getHighestprice()+ (response.body().getData().getResult().getHighestprice()*0.1)));
                        seekBar.setMin((int) (response.body().getData().getResult().getLowestprice()-(response.body().getData().getResult().getHighestprice()*0.1)));
                        seekBar.setProgress(response.body().getData().getResult().getAvgprice());


                    }else{

                    }
                }

                @Override
                public void onFailure(Call<SellDTO> call, Throwable t) {

                }
            });
        }
        return tradeVO;
    }
    public void loadImage(ViewGroup rootView){
        productModelService.productModel(ProductNameTemp.getInstance().getProductNameTemp()).enqueue(new Callback<ProductModelDTO>() {
            @Override
            public void onResponse(Call<ProductModelDTO> call, Response<ProductModelDTO> response) {
                if (response != null && response.body() != null && response.isSuccessful()) {
                    TextView productName = (TextView)rootView.findViewById(R.id.purchase_nemestuff_text);
                    ImageView Image = (ImageView)rootView.findViewById(R.id.purchase_mainpicture_image);

                    productName.setText(response.body().getData().getResult().getProductname());
                    Glide.with(rootView).load(response.body().getData().getResult().getProductpicture()).into(Image);

                }else{

                }

            }

            @Override
            public void onFailure(Call<ProductModelDTO> call, Throwable t) {

            }
        });
    }

    public void loadOrder(TradeVO tradeVO){
        BiddingTradeService.trade(tradeVO)
                .enqueue(new Callback<TradeDTO>() {
                    @Override
                    public void onResponse(Call<TradeDTO> call, Response<TradeDTO> response) {
                        if(response != null && response.body() != null && response.isSuccessful()) {
                            Toast.makeText(getContext(), response.body().getResponsemessage().toString(), Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getContext(), response.body().getResponsemessage().toString(), Toast.LENGTH_SHORT).show();

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