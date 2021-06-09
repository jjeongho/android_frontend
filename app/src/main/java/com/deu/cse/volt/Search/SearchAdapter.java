package com.deu.cse.volt.Search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deu.cse.volt.Dibs.DibsAdapter;
import com.deu.cse.volt.Dibs.DibsItem;
import com.deu.cse.volt.Main.Bidding.BiddingDTO;
import com.deu.cse.volt.Main.DetailThingsActivity;
import com.deu.cse.volt.Main.ProductNameTemp;
import com.deu.cse.volt.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    private Context context;
    private List<SearchDTO.Result> SearchList;

    public SearchAdapter(Context context, List<SearchDTO.Result> SearchList) {
        this.context = context;
        this.SearchList = SearchList;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycle_view, parent, false);

        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.BiddingModel.setText(String.valueOf(SearchList.get(position).getModelname()));
        holder.BiddingName.setText(String.valueOf(SearchList.get(position).getProductname()));
        Glide.with(context).load(SearchList.get(position).getProductpicture()).into(holder.BiddingImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String modelName = holder.BiddingName.getText().toString(); //holder로 가져온 값을 변수에 넣기
                String model = String.valueOf(SearchList.get(position).getModelname());   //modelname
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
        return SearchList.size();
    }


    public class SearchHolder extends RecyclerView.ViewHolder{
        TextView BiddingModel;
        TextView BiddingName;
        ImageView BiddingImage;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);
            BiddingName = (TextView)itemView.findViewById(R.id.search_re_things_text);
            BiddingModel = (TextView)itemView.findViewById(R.id.search_re_things_detail_text);
            BiddingImage = (ImageView) itemView.findViewById(R.id.search_re_main_image);
        }
    }
}