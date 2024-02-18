package com.example.linguini.HomeScreen.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.HomeScreen.model.repo.MealsRepository;
import com.example.linguini.HomeScreen.model.repo.MealsRepoIMP;
import com.example.linguini.HomeScreen.presenter.SearchPresenter;
import com.example.linguini.HomeScreen.presenter.SearchPresenterIMP;
import com.example.linguini.R;
import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView {

    private EditText txtSeachBar;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;
    private SearchPresenter searchPresenter;
    private MealsRepository mealsRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        txtSeachBar = rootView.findViewById(R.id.txtSeachBar);

        recyclerView = rootView.findViewById(R.id.searchRecyclerView);

        List<PojoForMeal> meals = new ArrayList<>();
        mealAdapter = new MealAdapter(getContext(), meals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mealAdapter);

        mealsRepository = MealsRepoIMP.getInstance(MealsRemoteDataSourceIMP.getInstance(getContext()));

        searchPresenter = new SearchPresenterIMP(mealsRepository, this);

        //searchPresenter.getSearchMeals("c");
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtSeachBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                Log.d("SearchFragment", "Text changed: " + query);
                performSearch(query);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }



    private void performSearch(String query) {
        Log.d("SearchFragment", "Performing search with query: " + query);
        if (!query.isEmpty()) {
            searchPresenter.getSearchMeals(query);
        } else {
            mealAdapter.setMeals(new ArrayList<>());
        }
    }

    @Override
    public void showSearchResults(MealResponse mealResponse) {
        List<PojoForMeal> meals = mealResponse.getMealDay();
        Log.d("SearchFragment", "Received search results: " + meals.size() + " meals");
        mealAdapter.setMeals(meals);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}