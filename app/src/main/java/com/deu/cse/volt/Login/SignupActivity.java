package com.deu.cse.volt.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deu.cse.volt.Main.MainActivity;
import com.deu.cse.volt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    SignUpInterface signUpService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpService = RetrofitSignUpServiceGenerator.createService(SignUpInterface.class);

        ImageView button = findViewById(R.id.signup_confirm_button);
        EditText id_edit = findViewById(R.id.signup_id_edittext);
        EditText pw_edit = findViewById(R.id.signup_pw_edittext);
        EditText email_edit = findViewById(R.id.signup_email_edittext);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getid = id_edit.getText().toString().trim();
                String getpw = pw_edit.getText().toString().trim();
                String getemail = email_edit.getText().toString().trim();

                //널값처리
                if(getid.getBytes().length <= 0 || getpw.getBytes().length <= 0 || getemail.getBytes().length <= 0  ){//빈값이 넘어올때의 처리
                    Toast.makeText(SignupActivity.this, "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    loadSignUpDTO(new SignUpBodyRequest(id_edit.getText().toString(),pw_edit.getText().toString(),email_edit.getText().toString()));
                }

            }
        });

    }
    public void loadSignUpDTO(SignUpBodyRequest signUpVO) {
        signUpService.getToken(signUpVO).enqueue(new Callback<SignUpDTO>() {
            @Override
            public void onResponse(Call<SignUpDTO> call, Response<SignUpDTO> response) {
                if (response.isSuccessful()) {
                    String result = response.body().getData().getResult().toString();
                    Intent intent = new Intent(getApplicationContext() , LoginActivity.class);

                    if(result.equals("true")){
                        Log.d("TEST",result);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(),response.body().getResponseMessage().toString(),Toast.LENGTH_LONG).show();
                        //무조건 수행

                    }else{
                        Toast.makeText(getApplicationContext(),response.body().getResponseMessage().toString(),Toast.LENGTH_LONG).show();
                    }



                    // response.body()
                    // response.body()에서 넘어오는 데이터로 Adapter에 뿌려주기
                } else {
                    Log.d("REST FAILED MESSAGE", response.body().getResponseMessage());
                }
            }

            @Override
            public void onFailure(Call<SignUpDTO> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
    }
}