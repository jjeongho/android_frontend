package com.deu.cse.volt.Search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.deu.cse.volt.Dibs.DibsAdapter;
import com.deu.cse.volt.Dibs.DibsItem;
import com.deu.cse.volt.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder> {
    private Context context;
    private List<SearchItem> list = new ArrayList<>();

    public SearchAdapter(Context context, List<SearchItem> list) {
        this.context = context;
        this.list = list;
    }

    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @Override
    public SearchAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycle_view, parent, false);
        SearchAdapter.Holder holder = new SearchAdapter.Holder(view);
        return holder;
    }

    /*
     * Todo 만들어진 ViewHolder에 data 삽입 ListView의 getView와 동일
     *
     * */
    @Override
    public void onBindViewHolder(SearchAdapter.Holder holder, int position) {
        // 각 위치에 문자열 세팅
        int itemposition = position;
        holder.wordText.setText(list.get(itemposition).word);
        holder.meaningText.setText(list.get(itemposition).meaning);
        Log.e("FragmentSearch", "onBindViewHolder" + itemposition);
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
            wordText = (TextView) view.findViewById(R.id.search_text_view);
            meaningText = (TextView) view.findViewById(R.id.search_text_view);
        }
    }


}
