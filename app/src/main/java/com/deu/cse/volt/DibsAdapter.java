package com.deu.cse.volt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DibsAdapter extends RecyclerView.Adapter<DibsAdapter.ViewHolder> {
    private ArrayList<DibsItem> mDibsList;

    @NonNull
    @Override
    public DibsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dibs_recycle_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DibsAdapter.ViewHolder holder, int position) {
        holder.onBind(mDibsList.get(position));
    }

    public void setFriendList(ArrayList<DibsItem> list){
        this.mDibsList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDibsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.recycle_image_view);
            name = (TextView) itemView.findViewById(R.id.recycle_text_view);

        }

        void onBind(DibsItem item){
            profile.setImageResource(item.getResourceId());
            name.setText(item.getName());
        }
    }
}