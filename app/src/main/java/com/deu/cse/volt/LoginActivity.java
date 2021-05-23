package com.deu.cse.volt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    //애니메이션로고
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //애니메이션 로고 효과
        iv = (ImageView) findViewById(R.id.login_logo_imageview);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        iv.startAnimation(animation);

        ImageView button = findViewById(R.id.login_login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}


