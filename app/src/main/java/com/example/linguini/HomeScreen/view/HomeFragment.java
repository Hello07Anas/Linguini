package com.example.linguini.HomeScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealArea;
import com.example.linguini.HomeScreen.model.repo.MealsRepoIMP;
import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;
import com.example.linguini.HomeScreen.presenter.HomePresenter;
import com.example.linguini.HomeScreen.presenter.HomePresenterIMP;
import com.example.linguini.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeView {



    private static final String TAG = "HomeFragment";
    HomePresenter homePresenter;
    MealAreaAdapter mealAreaAdapter;
    private RecyclerView recyclerView;
    private MealOfDayAdapter mealAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_card);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mealAdapter = new MealOfDayAdapter(new ArrayList<>());
        recyclerView.setAdapter(mealAdapter);
        return view;
    }//MealOfDayAdapter

    @Override
    public void showIngrediants(IngredientsResponse ingredientsResponse) {
        Log.i(TAG, "showIngrediants: " + ingredientsResponse.getIngredients());
    }

    @Override
    public void showIngrediantsErrorMSG(String error) {
        Log.i(TAG, "showIngrediants: " + error);
    }

    @Override
    public void showArea(MealAreaResponse mealAreaResponse) {
        Log.i(TAG, "showArea: " + mealAreaResponse.getMealAreas());

        // Ensure correct data extraction (adjust based on your response structure)
        List<PojoForMealArea> mealAreas = mealAreaResponse.getMealAreas();

        // Update adapter's dataset and trigger UI refresh
//        mealAreaAdapter.updateData(mealAreas);
//        mealAreaAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAreaErrorMSG(String error) {
        Log.i(TAG, "showArea: " + error);

    }

    @Override
    public void showMeal(MealResponse mealResponse) {
        //TODO  <<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        Log.i(TAG, "show Meal: " + mealResponse.getMealDay());
        List<PojoForMeal> meals = mealResponse.getMealDay();
        mealAdapter.updateData(meals);

    }


    @Override
    public void showMealErrorMSG(String error) {
        Log.i(TAG, "showArea: " + error);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter = new HomePresenterIMP(this, MealsRepoIMP.getInstance(MealsRemoteDataSourceIMP.getInstance(getActivity())));

        homePresenter.getIngrediants();
        homePresenter.getArea();
        homePresenter.getMeal();
        //homePresenter = new HomePresenterIMP(this, MealsRepoIMP.getInstance(MealsRemoteDataSourceIMP.getInstance(getActivity())));
    }

}