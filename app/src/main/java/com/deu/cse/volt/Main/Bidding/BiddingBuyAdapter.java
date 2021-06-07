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

import com.deu.cse.volt.Main.Bidding.BiddingStatus.BiddingPurchaseActivity;
import com.deu.cse.volt.R;

import java.util.List;

public class BiddingBuyAdapter extends RecyclerView.Adapter<BiddingBuyAdapter.BuyHolder> {
    private Context context;
    private List<BiddingDTO.Buy> BiddingList;

    public BiddingBuyAdapter(Context context, List<BiddingDTO.Buy> BiddingList){
        this.context = context;
        this.BiddingList = BiddingList;

    }


    @NonNull
    @Override
    public BiddingBuyAdapter.BuyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bidding_p_recycler_view, parent, false);
        return new BuyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BiddingBuyAdapter.BuyHolder holder, int position) {
        holder.BiddingPrice.setText(String.valueOf(BiddingList.get(position).getOrderprice()));
        holder.BiddingModel.setText(String.valueOf(BiddingList.get(position).getModelname()));
        holder.itemView.setTag(position); //커스텀 리스트 뷰의 각각의 리스트를 의미

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String getPosition = holder.BiddingPrice.getText().toString();
                  Intent intent;
                  intent = new Intent(context, BiddingPurchaseActivity.class);
                  intent.putExtra("Buy",getPosition);
                  v.getContext().startActivity(intent);
                  Log.e("Bidding",intent.toString());

            }
        });
    }

    @Override
    public int getItemCount() {
        return BiddingList.size();
    }

    public class BuyHolder extends RecyclerView.ViewHolder {
        TextView BiddingPrice;
        TextView BiddingModel;


        public BuyHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);
            BiddingPrice = (TextView)itemView.findViewById(R.id.bidding_p_price1_text);
            BiddingModel = (TextView)itemView.findViewById(R.id.bidding_p_purchase1_text);


        }
    }
}
