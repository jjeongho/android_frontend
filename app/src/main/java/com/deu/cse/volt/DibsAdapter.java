package com.deu.cse.volt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DibsAdapter extends RecyclerView.Adapter<DibsAdapter.Holder> {

    private Context context;
    private List<DibsItem> list = new ArrayList<>();

    public DibsAdapter(Context context, List<DibsItem> list) {
        this.context = context;
        this.list = list;
    }

    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dibs_recycle_view, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    /*
     * Todo 만들어진 ViewHolder에 data 삽입 ListView의 getView와 동일
     *
     * */
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 각 위치에 문자열 세팅
        int itemposition = position;
        holder.wordText.setText(list.get(itemposition).word);
        holder.meaningText.setText(list.get(itemposition).meaning);
        Log.e("FragmentDibs", "onBindViewHolder" + itemposition);
    }

    // 몇개의 데이터를 리스트로 뿌려줘야하는지 반드시 정의해줘야한다
    @Override
    public int getItemCount() {
        return list.size(); // RecyclerView의 size return
    }

    // ViewHolder는 하나의 View를 보존하는 역할을 한다
    public class Holder extends RecyclerView.ViewHolder{
        public TextView wordText;
        public TextView meaningText;

        public Holder(View view){
            super(view);
            wordText = (TextView) view.findViewById(R.id.recycle_text_view);
            meaningText = (TextView) view.findViewById(R.id.recycle_text_view);
        }
    }
}

