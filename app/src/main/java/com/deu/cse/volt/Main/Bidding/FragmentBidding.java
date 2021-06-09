package com.deu.cse.volt.Main.Bidding;

import android.graphics.Color;
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

import com.deu.cse.volt.Main.DetailThings.ChartDTO;
import com.deu.cse.volt.Main.DetailThings.ChartInterface;
import com.deu.cse.volt.RetrofitServiceGenerator.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.Main.ProductNameTemp;
import com.deu.cse.volt.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
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
    private ChartInterface chartService;
    private LineChart chart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.bidding_fragment, container, false);
        BiddingService = RetrofitBearerServiceGenerator.createService(BiddingInterface.class);
        chartService = RetrofitBearerServiceGenerator.createService(ChartInterface.class);
        loadSell(rootView);
        loadBuy(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        chart = (LineChart) view.findViewById(R.id.bidding_chart);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //chart.getAxisRight().setEnabled(false);
        chart.getLegend().setTextColor(Color.WHITE);
        chart.invalidate();
        chart.getXAxis().setDrawGridLines(false);

        chartService.chart(ProductNameTemp.getInstance().getProductNameTemp()).enqueue(new Callback<ChartDTO>() {

            @Override
            public void onResponse(Call<ChartDTO> call, Response<ChartDTO> response) {
                if (response.isSuccessful()) {

                    ArrayList<Entry> yValues = new ArrayList<>();
                    yValues.add(new Entry(0,response.body().getData().getResult().get(0).getAvgprice()));
                    yValues.add(new Entry(1,response.body().getData().getResult().get(1).getAvgprice()));
                    yValues.add(new Entry(2,response.body().getData().getResult().get(2).getAvgprice()));
                    yValues.add(new Entry(3,response.body().getData().getResult().get(3).getAvgprice()));
                    yValues.add(new Entry(4,response.body().getData().getResult().get(4).getAvgprice()));
                    yValues.add(new Entry(5,response.body().getData().getResult().get(5).getAvgprice()));
                    yValues.add(new Entry(6,response.body().getData().getResult().get(6).getAvgprice()));
                    yValues.add(new Entry(7,response.body().getData().getResult().get(7).getAvgprice()));

                    LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");

                    set1.setFillAlpha(110);
                    set1.setLineWidth(3f);
                    set1.setValueTextSize(10f);

                    ArrayList<ILineDataSet> datSets = new ArrayList<>();
                    datSets.add(set1);

                    LineData data1 = new LineData(datSets);
                    chart.setDrawGridBackground(false);
                    chart.setData(data1);

                }else{
                    Log.e("Chart",ProductNameTemp.getInstance().getProductNameTemp());
                }
            }

            @Override
            public void onFailure(Call<ChartDTO> call, Throwable t) {

            }
        });
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