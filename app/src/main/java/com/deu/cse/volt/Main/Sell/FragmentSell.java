package com.deu.cse.volt.Main.Sell;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.deu.cse.volt.Login.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;
import com.deu.cse.volt.Main.DetailThings.DetailThingsInterface;
import com.deu.cse.volt.Main.Home.HomeInterface;
import com.deu.cse.volt.Main.ProductNameTemp;
import com.deu.cse.volt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSell extends Fragment {
    String modelName;
    private SellStartInterface SellStartService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.sell_fragment, container, false);

        SellStartService = RetrofitBearerServiceGenerator.createService(SellStartInterface.class);
        loadStartSell(rootView);
//        if(getArguments() != null) { // bearertoken 받아오기
//            String token = getArguments().getString("bearertoken");
//        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void loadStartSell(ViewGroup rootView){
        SeekBar seekBar = (SeekBar)rootView.findViewById(R.id.sell_seekbar);
        TextView hopeBuyPrice = (TextView)rootView.findViewById(R.id.sell_hopebuyprice_text);
        TextView PaymentText = (TextView)rootView.findViewById(R.id.sell_paymenttext_text);
        ImageView sellImage = (ImageView)rootView.findViewById(R.id.sell_mainpicture_image);
        TextView sellImageText = (TextView)rootView.findViewById(R.id.sell_nemestuff_text);
        TextView sellImageModelText = (TextView)rootView.findViewById(R.id.sell_modeltuff_text);


        SellStartService.sell(ProductNameTemp.getInstance().getProductNameTemp()).enqueue(new Callback<SellDTO>() {
            @Override
            public void onResponse(Call<SellDTO> call, Response<SellDTO> response) {
                if (response.isSuccessful()) {
                    Log.d("Next!",response.body().getData().getResult().getProductname());
                    Glide.with(rootView).load(response.body().getData().getResult().getProductpicture()).into(sellImage);
                    sellImageText.setText(response.body().getData().getResult().getProductname());
                    sellImageModelText.setText(response.body().getData().getResult().getModelname());
                    seekBar.setProgress(response.body().getData().getResult().getShippingprice());
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override//볼륨은 조절해도되지만 화면조절은 버벅될 우려가 있음
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        hopeBuyPrice.setText(+seekBar.getProgress() + "0,000  원");
                        PaymentText.setText(+seekBar.getProgress() + "0,000  원");
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


                }else{

                }
            }

            @Override
            public void onFailure(Call<SellDTO> call, Throwable t) {

            }
        });

    }
}