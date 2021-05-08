package com.deu.cse.volt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager manager;
    FragmentTransaction ft;

    FragmentHome fragmentHome;
    FragmentSearch fragmentSearch;
    FragmentDibs fragmentDibs;
    FragmentMyinfo fragmentMyinfo;
    FragmentSettings fragmentSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        Button home = findViewById(R.id.home_btn);
        Button search = findViewById(R.id.search_btn);
        Button dibs = findViewById(R.id.dibs_btn);
        Button myinfo = findViewById(R.id.myinfo_btn);
        Button settings = findViewById(R.id.settings_btn);

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
    }

    @Override
    public void onClick(View v) {
        ft = manager.beginTransaction();

        int id = v.getId();
        switch (id) {
            case R.id.home_btn:
                ft.replace(R.id.fragment_container, fragmentHome);
                ft.commit();
                break;
            case R.id.search_btn:
                ft.replace(R.id.fragment_container, fragmentSearch);
                ft.commit();
                break;
            case R.id.dibs_btn:
                ft.replace(R.id.fragment_container, fragmentDibs);
                ft.commit();
                break;
            case R.id.myinfo_btn:
                ft.replace(R.id.fragment_container, fragmentMyinfo);
                ft.commit();
                break;
            case R.id.settings_btn:
                ft.replace(R.id.fragment_container, fragmentSettings);
                ft.commit();
                break;
        }
    }
}