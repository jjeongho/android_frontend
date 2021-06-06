package com.deu.cse.volt.Main.Bidding;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deu.cse.volt.Main.FragmentPurchase;
import com.deu.cse.volt.Main.FragmentSell;
import com.deu.cse.volt.R;

import java.util.List;

public class BiddingSellAdapter extends RecyclerView.Adapter<BiddingSellAdapter.SellHolder> {
    private Context context;
    private List<BiddingDTO.Sell> SellList;

    public BiddingSellAdapter(Context context, List<BiddingDTO.Sell> SellList){
        this.context = context;
        this.SellList = SellList;
    }

    @NonNull
    @Override
    public BiddingSellAdapter.SellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bidding_s_recycler_view, parent, false);
        return new SellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellHolder holder, int position) {
        holder.BiddingPrice.setText(String.valueOf(SellList.get(position).getOrderprice()));
        holder.BiddingModel.setText(String.valueOf(SellList.get(position).getModelname()));
        holder.itemView.setTag(position); //커스텀 리스트 뷰의 각각의 리스트를 의미

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BiddingModel = holder.BiddingModel.getText().toString();
                Intent intent = new Intent(v.getContext(), FragmentSell.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("BiddingModel",BiddingModel);
                Log.e("Bidding",intent.toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return SellList.size();
    }

    public class SellHolder extends RecyclerView.ViewHolder {
        TextView BiddingPrice;
        TextView BiddingModel;
        public SellHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);
            BiddingPrice = (TextView)itemView.findViewById(R.id.bidding_s_price1_text);
            BiddingModel = (TextView)itemView.findViewById(R.id.bidding_s_sell1_text);

        }
    }
}
