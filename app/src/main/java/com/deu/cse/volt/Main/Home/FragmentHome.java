package com.deu.cse.volt.Main.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deu.cse.volt.Login.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.Main.DetailThingsActivity;
import com.deu.cse.volt.Main.Home.HomeAdapter;
import com.deu.cse.volt.Main.Home.HomeDTO;
import com.deu.cse.volt.Main.Home.HomeInterface;
import com.deu.cse.volt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    private HomeInterface HomeService;
    private List<HomeDTO.Result> HomeList;
    private HomeDTO dataInfo;

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.home_fragment, container, false);
        HomeService = RetrofitBearerServiceGenerator.createService(HomeInterface.class);
        LinearLayout click = (LinearLayout) rootView.findViewById(R.id.main_banner_layout);
        loadHomeDTO(rootView);
        loadMainBanner(rootView);

//        if(getArguments() != null) { // bearertoken 받아오기
//            String token = getArguments().getString("bearertoken");
//            Log.e("TOKEN_HOME", token);
//        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadMainBanner(ViewGroup rootView){
        ImageView mainImage = (ImageView) rootView.findViewById(R.id.main_mainimage_image);
        TextView mainText = (TextView) rootView.findViewById(R.id.main_imagetext_text);
        TextView brandText = (TextView) rootView.findViewById(R.id.main_imagebrand_text);
        TextView priceText = (TextView) rootView.findViewById(R.id.main_timetext_text);

        HomeService.HomeList().enqueue(new Callback<HomeDTO>() {
            @Override
            public void onResponse(Call<HomeDTO> call, Response<HomeDTO> response) {
                if (response.isSuccessful()) {
                    mainText.setText(response.body().getData().getResult().get(0).getProductname());
                    brandText.setText(response.body().getData().getResult().get(0).getManufacturer());
                    priceText.setText(Integer.toString(response.body().getData().getResult().get(0).getShippingprice()));
                    Glide.with(rootView).load(response.body().getData().getResult().get(0).getProductpicture()).into(mainImage);
                }else{
//                    Log.e("onfailed",response.body().getData().getResult().toString());

                }
            }

            @Override
            public void onFailure(Call<HomeDTO> call, Throwable t) {

            }
        });
    }


    public void loadHomeDTO(ViewGroup rootView){
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.home_recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        HomeService.HomeList().enqueue(new Callback<HomeDTO>() {
            @Override
            public void onResponse(Call<HomeDTO> call, Response<HomeDTO> response) {
                if (response.isSuccessful()) {
                    HomeList =response.body().getData().getResult();
                    homeAdapter = new HomeAdapter(getContext(),HomeList);
                    recyclerView.setAdapter(homeAdapter);
                }
                else{
//                    Log.e("onfailed",response.body().getData().getResult().toString());
                }
            }

            @Override
            public void onFailure(Call<HomeDTO> call, Throwable t) {
                //Log.d("HomeFragment",t.toString());
            }
        });

    }
}