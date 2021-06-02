package com.deu.cse.volt.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deu.cse.volt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IDsearchActivity extends AppCompatActivity {
    IDsearchInterface idsearchService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idsearch);

        idsearchService = RetrofitIDsearchServiceGenerator.createService(IDsearchInterface.class);

        ImageView button = findViewById(R.id.idsearch_confirm_button);
        EditText email_edit = findViewById(R.id.idsearch_email_edittext);
        TextView output = (TextView) findViewById(R.id.text_output);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getemail = email_edit.getText().toString().trim();
                //널값처리
                if( getemail.getBytes().length <= 0 ){//빈값이 넘어올때의 처리
                    Toast.makeText(IDsearchActivity.this, "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    loadIDsearchDTO(new IDsearchBodyRequest(email_edit.getText().toString()));
                    output.setText(email_edit.getText().toString());

                }

            }
        });
    }


    public void loadIDsearchDTO(IDsearchBodyRequest iDsearchBodyRequest) {
        idsearchService.idsearch(iDsearchBodyRequest.getEmail()).enqueue(new Callback<IDsearchDTO>() {
            @Override
            public void onResponse(Call<IDsearchDTO> call, Response<IDsearchDTO> response) {
                if (response.isSuccessful()) {
                    String result = response.body().getData().getResult().toString();
                    Intent intent = new Intent(getApplicationContext() , LoginActivity.class);

                    if(result.equals("true")){
                        Log.d("TEST",result);
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
            public void onFailure(Call<IDsearchDTO> call, Throwable t) {
                Log.d("REST ERROR!", t.getMessage());
            }
        });
    }
}