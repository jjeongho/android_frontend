package com.deu.cse.volt.Main.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deu.cse.volt.Login.BearerTokenTemp;
import com.deu.cse.volt.Main.DetailThingsActivity;
import com.deu.cse.volt.Main.ProductNameTemp;
import com.deu.cse.volt.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context context;
    private List<HomeDTO.Result> HomeList;

    public HomeAdapter(Context context, List<HomeDTO.Result> HomeList){
        this.context = context;
        this.HomeList = HomeList;

    }
    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_view, parent, false);
        return new MyViewHolder(view);
    }
;
    @Override//intent 값전
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.modelName.setText(String.valueOf(HomeList.get(position).getProductname())); //productname
        holder.modelPrice.setText(String.valueOf(HomeList.get(position).getShippingprice())+"원");
        holder.modelCreated.setText(String.valueOf(HomeList.get(position).getCreatedAt()));
        holder.itemView.setTag(position); //커스텀 리스트 뷰의 각각의 리스트를 의미

        //리스트 클릭 이벤트
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String modelName = holder.modelName.getText().toString(); //holder로 가져온 값을 변수에 넣기
                String model = String.valueOf(HomeList.get(position).getModelname());   //modelname
                ProductNameTemp.getInstance().setProductNameTemp(model); // bearertokentemp 클래스에 저장

                
                Intent intent;//인텐트 선언
                intent = new Intent(context, DetailThingsActivity.class); //look_memo.class부분에 원하는 화면 연결
                intent.putExtra("modelName", modelName); //변수값 인텐트로 넘기기
                context.startActivity(intent); //액티비티 열기

            }
        });
    }
    @Override
    public int getItemCount() {
        Log.e("HomeList",Integer.toString(HomeList.size()));
        return HomeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView modelName;
        TextView modelPrice;
        TextView modelCreated;
        LinearLayout click;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);

            modelName = (TextView)itemView.findViewById(R.id.home_re_name_text);
            modelPrice = (TextView)itemView.findViewById(R.id.home_re_price_text);
            modelCreated = (TextView)itemView.findViewById(R.id.home_re_sale_text);

        }
    }

}
