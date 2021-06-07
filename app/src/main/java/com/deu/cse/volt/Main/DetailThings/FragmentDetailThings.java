package com.deu.cse.volt.Main.DetailThings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.deu.cse.volt.Login.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.Main.DetailThings.DetailThingsDTO;
import com.deu.cse.volt.Main.DetailThings.DetailThingsInterface;
import com.deu.cse.volt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailThings extends Fragment {
    private DetailThingsInterface DetailThingsService;
    private Intent intent; //인텐트 선언
    String modelName;

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
        ImageView mainImage = (ImageView) rootView.findViewById(R.id.detail_main_image);


        modelName = getActivity().getIntent().getExtras().getString("modelName");//인텐트값가져오기

        DetailThingsService.detailThings(modelName).enqueue(new Callback<DetailThingsDTO>() {
            @Override
            public void onResponse(Call<DetailThingsDTO> call, Response<DetailThingsDTO> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse",response.body().getData().getResult().get(0).getCreatedAt());
                    mainText.setText(response.body().getData().getResult().get(0).getProductname());
                    mainPrice.setText(Integer.toString(response.body().getData().getResult().get(0).getShippingprice()));
                    brandText.setText(response.body().getData().getResult().get(0).getManufacturer());
                    modelText.setText(response.body().getData().getResult().get(0).getModelname());
                    createdText.setText(response.body().getData().getResult().get(0).getCreatedAt());
                    Glide.with(rootView).load(response.body().getData().getResult().get(0).getProductpicture()).into(mainImage);

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