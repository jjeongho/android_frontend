package com.deu.cse.volt.Search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deu.cse.volt.RetrofitServiceGenerator.RetrofitBearerServiceGenerator;
import com.deu.cse.volt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment {
    private RecyclerView recyclerView;
    private SearchInterface SearchService;
    private SearchAdapter searchAdapter;
    private List<SearchDTO.Result> SearchList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.search_fragment, container, false);
        SearchService = RetrofitBearerServiceGenerator.createService(SearchInterface.class);

        loadSearch(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    public void loadSearch(ViewGroup rootView){
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.search_recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        SearchView searchView = (SearchView) rootView.findViewById(R.id.search_view);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    SearchService.search(newText.toString()).enqueue(new Callback<SearchDTO>() {
                        @Override
                        public void onResponse(Call<SearchDTO> call, Response<SearchDTO> response) {
                            if(response != null && response.body() != null && response.isSuccessful()) {
                                Log.i("Search",searchView.toString());
                                Log.d("Search",searchView.toString());
                                SearchList = response.body().getData().getResult();
                                searchAdapter = new SearchAdapter(getContext(),SearchList);
                                recyclerView.setAdapter(searchAdapter);

                            }else{
                                Log.e("Search",searchView.toString());

                            }
                        }

                        @Override
                        public void onFailure(Call<SearchDTO> call, Throwable t) {

                        }
                    });
                      return false;
                }
            });
    }
}