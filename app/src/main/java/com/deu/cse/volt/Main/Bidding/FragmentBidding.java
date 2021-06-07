package com.deu.cse.volt.Main.Bidding;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deu.cse.volt.Login.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.Main.ProductNameTemp;
import com.deu.cse.volt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBidding extends Fragment {
    private BiddingInterface BiddingService;
    private List<BiddingDTO.Sell> BiddingSellList;
    private List<BiddingDTO.Buy> BiddingBuyList;
    private BiddingBuyAdapter biddingBuyAdapter;
    private BiddingSellAdapter biddingSellAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.bidding_fragment, container, false);
        BiddingService = RetrofitBearerServiceGenerator.createService(BiddingInterface.class);
        loadSell(rootView);
        loadBuy(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadSell(ViewGroup rootView){
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.bidding_sell_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        BiddingService.bidding(ProductNameTemp.getInstance().getProductNameTemp()).enqueue(new Callback<BiddingDTO>() {
            @Override
            public void onResponse(Call<BiddingDTO> call, Response<BiddingDTO> response) {
                if (response != null && response.body() != null && response.isSuccessful()) {
                    BiddingSellList = response.body().getData().getSell();
                    biddingSellAdapter = new BiddingSellAdapter(getContext(),BiddingSellList);
                    recyclerView.setAdapter(biddingSellAdapter);
                }else{
                    Log.e("onfailed",response.body().getData().getSell().toString());
                }
                }

            @Override
            public void onFailure(Call<BiddingDTO> call, Throwable t) {

            }
        });
    }

    public void loadBuy(ViewGroup rootView){
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.bidding_purchase_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        BiddingService.bidding(ProductNameTemp.getInstance().getProductNameTemp()).enqueue(new Callback<BiddingDTO>() {
            @Override
            public void onResponse(Call<BiddingDTO> call, Response<BiddingDTO> response) {
                if (response != null && response.body() != null && response.isSuccessful() ) {
                    BiddingBuyList = response.body().getData().getBuy();
                    biddingBuyAdapter = new BiddingBuyAdapter(getContext(),BiddingBuyList);
                    recyclerView.setAdapter(biddingBuyAdapter);
                }else{
                    Log.e("onfailed",response.body().getData().getSell().toString());
                }
            }


            @Override
            public void onFailure(Call<BiddingDTO> call, Throwable t) {

            }
        });
    }

}