package com.deu.cse.volt.Main.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        ImageView main_banner_image = (ImageView) rootView.findViewById(R.id.main_banner_image);
        HomeService = RetrofitBearerServiceGenerator.createService(HomeInterface.class);
        loadHomeDTO(rootView);


//        if(getArguments() != null) { // bearertoken 받아오기
//            String token = getArguments().getString("bearertoken");
//            Log.e("TOKEN_HOME", token);
//        }

        main_banner_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailThingsActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                    Log.e("onfailed",response.body().getData().getResult().toString());
                }
            }

            @Override
            public void onFailure(Call<HomeDTO> call, Throwable t) {
                Log.d("HomeFragment",t.toString());
            }
        });

    }
}