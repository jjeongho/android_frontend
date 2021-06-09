package com.deu.cse.volt.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deu.cse.volt.Login.IDsearch.IdSearchActivity;
import com.deu.cse.volt.RetrofitServiceGenerator.RetrofitLoginServiceGenerator;
import com.deu.cse.volt.RetrofitServiceGenerator.RetrofitSignUpServiceGenerator;
import com.deu.cse.volt.Login.SignUp.SignUpInterface;
import com.deu.cse.volt.Login.SignUp.SignupActivity;
import com.deu.cse.volt.Main.Home.MainActivity;
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
        TextView signup_btn = findViewById(R.id.login_signup_button);
        TextView idsearch_btn = findViewById(R.id.login_idsearch_button);
        TextView pwdsearch_btn = findViewById(R.id.login_pwdsearch_button);
        EditText id_edit = findViewById(R.id.login_id_edittext);
        EditText pw_edit = findViewById(R.id.login_pw_edittext);

        // 화면전환

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLoginDTO(new LoginVO(id_edit.getText().toString(),pw_edit.getText().toString(),"password"));


            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);

                startActivity(intent);

            }
        });

        idsearch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IdSearchActivity.class);

                startActivity(intent);

            }
        });

        pwdsearch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PWDsearchActivity.class);

                startActivity(intent);

            }
        });
    }
  /*  public void loadSignDTO(UserVO userVO) {

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
                Log.d("REST ERROR!`", t.getMessage());
            }
        });
    }*/

    public LoginVO loadLoginDTO(LoginVO loginVO) {
        loginService.getToken(loginVO.getUsername(), loginVO.getPassword(), loginVO.getGrant_type()).enqueue(new Callback<LoginDTO>() {
            @Override
            public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                if (response.isSuccessful()) {//아이디나 비번이 맞으면 화면을 이동 그리고 bearertoken을 넘겨준다.
                    Log.d("TEST",response.body().getAccess_token());

                    String temp = response.body().getAccess_token(); //bearer token 발급 받

                    BearerTokenTemp bt = BearerTokenTemp.getInstance(); // bearertokentemp 클래스에 저장
                    bt.setBearerToken(temp);



                    Intent intent = new Intent(getApplicationContext() ,MainActivity.class);
                    intent.putExtra("Data", temp);
                    startActivity(intent);
                    finish();


                    // response.body()
                    // response.body()에서 넘어오는 데이터로 Adapter에 뿌려주기
                } else {
                    String msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

                    Log.d("REST FAILED MESSAGE", response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
        return loginVO;
    }

}



