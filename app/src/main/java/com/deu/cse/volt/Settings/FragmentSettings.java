package com.deu.cse.volt.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.deu.cse.volt.Main.MainActivity;
import com.deu.cse.volt.R;

public class FragmentSettings extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.settings_fragment, container, false);
        ImageView myinfo_btn = (ImageView) rootView.findViewById(R.id.settings_myinfo_image);
        ImageView notice_btn = (ImageView) rootView.findViewById(R.id.settings_notice_image);
        ImageView alarm_btn = (ImageView) rootView.findViewById(R.id.settings_alarm_image);
        ImageView payment_btn = (ImageView) rootView.findViewById(R.id.settings_payment_image);
        ImageView security_btn = (ImageView) rootView.findViewById(R.id.settings_security_image);
        ImageView agree_btn = (ImageView) rootView.findViewById(R.id.settings_agree_image);
        ImageView qna_btn = (ImageView) rootView.findViewById(R.id.settings_qna_image);
        ImageView inquiry_btn = (ImageView) rootView.findViewById(R.id.settings_inquiry_image);


        if(getArguments() != null) { // bearertoken 받아오기
            String token = getArguments().getString("bearertoken");
            Log.e("TOKEN_SETTINGS", token);

        }

        //프래그먼트에서 액티비티 이동
        myinfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyinfoModifyActivity.class);
                startActivity(intent);
            }
        });

        notice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AlarmModifyActivity.class);
                startActivity(intent);
            }
        });

        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });

        security_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecurityActivity.class);
                startActivity(intent);
            }
        });

        agree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AgreeActivity.class);
                startActivity(intent);
            }
        });

        qna_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QnaActivity.class);
                startActivity(intent);
            }
        });

        inquiry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InquiryActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}