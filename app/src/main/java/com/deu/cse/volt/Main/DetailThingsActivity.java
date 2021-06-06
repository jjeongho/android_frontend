package com.deu.cse.volt.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;

import com.deu.cse.volt.R;

public class DetailThingsActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager manager;
    FragmentTransaction ft_dt;

    FragmentBidding fragmentBidding;
    FragmentSell fragmentSell;
    FragmentPurchase fragmentPurchase;
    FragmentDetailThings fragmentDetailThings;

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_things);

        manager = getSupportFragmentManager();

        ImageView bidding = findViewById(R.id.detail_bidding_btn);
        ImageView sell = findViewById(R.id.detail_sell_btn);
        ImageView purchase = findViewById(R.id.detail_purchase_btn);

        fragmentBidding = new FragmentBidding();
        fragmentSell = new FragmentSell();
        fragmentPurchase = new FragmentPurchase();
        fragmentDetailThings = new FragmentDetailThings();


        ft_dt = manager.beginTransaction();
        ft_dt.add(R.id.fragment_container_detail, fragmentDetailThings);
        ft_dt.addToBackStack(null);
        ft_dt.commit();

        bidding.setOnClickListener(this);
        sell.setOnClickListener(this);
        purchase.setOnClickListener(this);

        chart = findViewById(R.id.detail_chart);

        ArrayList<Entry> values = new ArrayList<>();

        // 그래프 랜덤 값
        for(int i =0; i<10; i++){
            float val = (float) (Math.random() * 10);
            values.add(new Entry(i, val));
        }

        LineDataSet set1;
        set1 = new LineDataSet(values, "최근 가격");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        // 줄 색깔, 포인트 색깔, 배경 색깔
        set1.setColor(Color.BLUE);
        set1.setCircleColor(Color.BLUE);
        chart.setBackgroundColor(Color.WHITE);


        chart.setData(data);
    }

    @Override
    public void onClick(View v) {
        ft_dt = manager.beginTransaction();

        int id = v.getId();
        switch (id) {
            case R.id.detail_bidding_btn:
                ft_dt.replace(R.id.fragment_container_detail, fragmentBidding);
                ft_dt.commit();
                break;
            case R.id.detail_sell_btn:
                ft_dt.replace(R.id.fragment_container_detail, fragmentSell);
                ft_dt.commit();
                break;
            case R.id.detail_purchase_btn:
                ft_dt.replace(R.id.fragment_container_detail, fragmentPurchase);
                ft_dt.commit();
                break;
        }
    }
}