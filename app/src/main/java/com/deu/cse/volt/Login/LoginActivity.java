package com.deu.cse.volt.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.deu.cse.volt.Main.MainActivity;
import com.deu.cse.volt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //애니메이션로고
    ImageView iv;
    SignUpInterface signUpService;
    LoginInterface loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpService = RetrofitSignUpServiceGenerator.createService(SignUpInterface.class);
        loginService = RetrofitLoginServiceGenerator.createService(LoginInterface.class);
        //애니메이션 로고 효과
        iv = (ImageView) findViewById(R.id.login_logo_imageview);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        iv.startAnimation(animation);

        ImageView button = findViewById(R.id.login_login_button);
        loadLoginDTO(new LoginVO("choko","hwang","password"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
//                loadSignDTO(new LoginVO("testtest","test"));

                finish();
            }
        });
    }

    public void loadSignDTO(UserVO userVO) {

        signUpService.signUp(userVO).enqueue(new Callback<SignUpDTO>() {
            @Override
            public void onResponse(Call<SignUpDTO> call, Response<SignUpDTO> response) {
                if (response.isSuccessful()) {
                    Log.d("TEST",response.body().getUsername());
                    // response.body()
                    // response.body()에서 넘어오는 데이터로 Adapter에 뿌려주기
                } else {
                    Log.d("REST FAILED MESSAGE", response.message());
                }
            }

            @Override
            public void onFailure(Call<SignUpDTO> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
    }

    public void loadLoginDTO(LoginVO loginVO) {

        loginService.getToken(loginVO.getUsername(), loginVO.getPassword(), loginVO.getGrant_type()).enqueue(new Callback<LoginDTO>() {
            @Override
            public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                if (response.isSuccessful()) {
                    Log.d("TEST",response.body().getAccess_token());
                    // response.body()
                    // response.body()에서 넘어오는 데이터로 Adapter에 뿌려주기
                } else {
                    Log.d("REST FAILED MESSAGE", response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
    }

}


