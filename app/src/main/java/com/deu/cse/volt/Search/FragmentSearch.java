package com.deu.cse.volt.Search;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deu.cse.volt.Dibs.DibsAdapter;
import com.deu.cse.volt.Dibs.DibsItem;
import com.deu.cse.volt.R;

import java.util.ArrayList;

public class FragmentSearch extends Fragment {
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private ArrayList<SearchItem> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.search_fragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.search_recyclerView);
        
        list = SearchItem.createContactsList(5);
        recyclerView.setHasFixedSize(true);
        adapter = new SearchAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(), 1));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}