package com.deu.cse.volt.Login.IDsearch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.deu.cse.volt.Login.LoginActivity;
import com.deu.cse.volt.Login.SignUp.SignupActivity;
import com.deu.cse.volt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdSearchActivity extends AppCompatActivity {
    private IdSearchInterface IdSearchService;
    private TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idsearch);

        IdSearchService = RetrofitIDsearchServiceGenerator.createService(IdSearchInterface.class);
        ImageView back_btn = findViewById(R.id.imageBack);
        ImageView button = findViewById(R.id.idsearch_confirm_button);
        EditText email_edit = findViewById(R.id.idsearch_email_edittext);
        tv = findViewById(R.id.text_output);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_email = email_edit.getText().toString().trim();
                //널값처리
                if( get_email.getBytes().length <= 0 ){//빈값이 넘어올때의 처리

                    Toast.makeText(IdSearchActivity.this, "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    loadIdSearchDTO(new IdSearchBodyRequest(email_edit.getText().toString()));
                }

            }
        });
    }


    public void loadIdSearchDTO(IdSearchBodyRequest iDsearchBodyRequest) {
        IdSearchService.idsearch(iDsearchBodyRequest.getEmail()).enqueue(new Callback<IdSearchDTO>() {
            @Override
            public void onResponse(Call<IdSearchDTO> call, Response<IdSearchDTO> response) {
                if (response.isSuccessful()) {
                    String result = response.body().getData().getResult().toString();
                    Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
                    if(result.equals("true")){
                        Log.d("TEST",result);
                        tv.setText(result);
                        Toast.makeText(getApplicationContext(),response.body().getData().getResult().toString(),Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<IdSearchDTO> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
    }
}