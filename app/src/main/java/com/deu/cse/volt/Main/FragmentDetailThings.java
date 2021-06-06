package com.deu.cse.volt.Main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.deu.cse.volt.Login.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.R;

import java.util.EmptyStackException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailThings extends Fragment {
    private DetailThingsInterface DetailThingsService;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.detailthings_fragment, container, false);
        DetailThingsService = RetrofitBearerServiceGenerator.createService(DetailThingsInterface.class);
        loadDetailThingsDTO(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void loadDetailThingsDTO(ViewGroup rootView) {
        TextView mainText = (TextView) rootView.findViewById(R.id.detail_main_text);
        TextView mainPrice = (TextView) rootView.findViewById(R.id.detail_price_text);
        TextView brandText = (TextView) rootView.findViewById(R.id.detail_detailbrand_text);
        TextView modelText = (TextView) rootView.findViewById(R.id.detail_detailmodelname_text);
        TextView createdText = (TextView) rootView.findViewById(R.id.detail_detailreleasedate_text);

        DetailThingsService.detailThings("갤럭시 S21 ULTRA").enqueue(new Callback<DetailThingsDTO>() {
            @Override
            public void onResponse(Call<DetailThingsDTO> call, Response<DetailThingsDTO> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse",response.body().getData().getResult().get(0).getCreatedAt());
                    mainText.setText(response.body().getData().getResult().get(0).getProductname());
                    mainPrice.setText(Integer.toString(response.body().getData().getResult().get(0).getShippingprice()));
                    brandText.setText(response.body().getData().getResult().get(0).getManufacturer());
                    modelText.setText(response.body().getData().getResult().get(0).getModelname());
                    createdText.setText(response.body().getData().getResult().get(0).getCreatedAt());
                }
                else{
                    Log.d("REST FAILED MESSAGE", response.body().getResponsemessage());

                }
            }


            @Override
            public void onFailure(Call<DetailThingsDTO> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });


    }
}