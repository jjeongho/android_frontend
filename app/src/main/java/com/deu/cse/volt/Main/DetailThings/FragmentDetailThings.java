package com.deu.cse.volt.Main.DetailThings;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.deu.cse.volt.Main.Bidding.BiddingStatus.BiddingTradeInterface;
import com.deu.cse.volt.Main.ProductNameTemp;
import com.deu.cse.volt.RetrofitServiceGenerator.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentDetailThings extends Fragment {
    private DetailThingsInterface DetailThingsService;
    private ChartInterface chartService;
    private Intent intent; //인텐트 선언
    String modelName;
    private LineChart chart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.detailthings_fragment, container, false);
        DetailThingsService = RetrofitBearerServiceGenerator.createService(DetailThingsInterface.class);
        chartService = RetrofitBearerServiceGenerator.createService(ChartInterface.class);
        loadDetailThingsDTO(rootView);
        loadAI(rootView);
        loadChart(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chart = (LineChart) view.findViewById(R.id.detail_chart);
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


    public void loadChart(ViewGroup rootView){




    }



    public void loadAI(ViewGroup rootView){
        TextView mainPrice = (TextView) rootView.findViewById(R.id.detail_price_text);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://113.198.235.227:20204/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(
                new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        return null;
                    }
                }
        ).build();

        AiInterface AiInterService = retrofit.create(AiInterface.class);
        modelName = getActivity().getIntent().getExtras().getString("modelName");//인텐트값가져오기

        AiInterService.ai(modelName).enqueue(new Callback<AiDTO>() {
            @Override
            public void onResponse(Call<AiDTO> call, Response<AiDTO> response) {
                if (response.isSuccessful()) {
                    mainPrice.setText(Integer.toString(response.body().getData())+ " 원");
                }else{


                }
                }
            @Override
            public void onFailure(Call<AiDTO> call, Throwable t) {

            }
        });

    }


    public void loadDetailThingsDTO(ViewGroup rootView) {
        TextView mainText = (TextView) rootView.findViewById(R.id.detail_main_text);
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
                    brandText.setText(response.body().getData().getResult().get(0).getManufacturer());
                    modelText.setText(response.body().getData().getResult().get(0).getModelname());
                    createdText.setText(response.body().getData().getResult().get(0).getCreatedAt());
                    Glide.with(rootView).load(response.body().getData().getResult().get(0).getProductpicture()).into(mainImage);

                }
                else{
                    //Log.d("REST FAILED MESSAGE", response.body().getResponsemessage());

                }
            }

            @Override
            public void onFailure(Call<DetailThingsDTO> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
    }


}