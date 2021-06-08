package com.deu.cse.volt.Search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deu.cse.volt.Dibs.DibsAdapter;
import com.deu.cse.volt.Dibs.DibsItem;
import com.deu.cse.volt.Main.Bidding.BiddingDTO;
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
    public void onBindViewHolder(@NonNull SearchAdapter.SearchHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return SearchList.size();
    }


    public class SearchHolder extends RecyclerView.ViewHolder{

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);

        }
    }
}