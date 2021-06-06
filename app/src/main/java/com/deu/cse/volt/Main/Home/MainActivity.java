package com.deu.cse.volt.Main.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.deu.cse.volt.Dibs.FragmentDibs;
import com.deu.cse.volt.Myinfo.FragmentMyinfo;
import com.deu.cse.volt.R;
import com.deu.cse.volt.Search.FragmentSearch;
import com.deu.cse.volt.Settings.FragmentSettings;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager manager;
    FragmentTransaction ft;

    FragmentHome fragmentHome;
    FragmentSearch fragmentSearch;
    FragmentDibs fragmentDibs;
    FragmentMyinfo fragmentMyinfo;
    FragmentSettings fragmentSettings;

//    Intent intent = getIntent();
//    String token = getIntent().getStringExtra("Data");
    private String token;


    Bundle bundle = new Bundle(); //프래그먼트간의 데이터값 전달을 위한 번들 생성
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //프래그먼트 전환//
        token = getIntent().getStringExtra("Data");
        Log.e("TOKEN_TEST", token);
        manager = getSupportFragmentManager();

        TextView home = findViewById(R.id.home_btn);
        TextView search = findViewById(R.id.search_btn);
        TextView dibs = findViewById(R.id.dibs_btn);
        TextView myinfo = findViewById(R.id.myinfo_btn);
        TextView settings = findViewById(R.id.settings_btn);

        fragmentHome = new FragmentHome();
        fragmentSearch = new FragmentSearch();
        fragmentDibs = new FragmentDibs();
        fragmentMyinfo = new FragmentMyinfo();
        fragmentSettings = new FragmentSettings();


        ft = manager.beginTransaction();
        ft.add(R.id.fragment_container, fragmentHome);
        ft.addToBackStack(null);
        ft.commit();

        home.setOnClickListener(this);
        search.setOnClickListener(this);
        dibs.setOnClickListener(this);
        myinfo.setOnClickListener(this);
        settings.setOnClickListener(this);
        //프래그먼트 전환//

      //  fragmentMyinfo.setArguments(bundle);
        //fragmentSettings.setArguments(bundle);

    }

    @Override
    public void onClick(View v) {
        ft = manager.beginTransaction();
        System.out.println("다");
        bundle.putString("bearertoken", token);
        int id = v.getId();
        switch (id) {
            case R.id.home_btn:
                fragmentHome.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragmentHome);
                ft.commit();
                break;
            case R.id.search_btn:
                fragmentSearch.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragmentSearch);
                ft.commit();
                break;
            case R.id.dibs_btn:
                fragmentDibs.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragmentDibs);
                ft.commit();
                break;
            case R.id.myinfo_btn:
                fragmentMyinfo.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragmentMyinfo);
                ft.commit();
                break;
            case R.id.settings_btn:
                fragmentSettings.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragmentSettings);
                ft.commit();
                break;
        }
    }
}