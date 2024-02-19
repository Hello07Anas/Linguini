package com.example.linguini.HomeScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealCategoryResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealArea;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealCategory;
import com.example.linguini.HomeScreen.model.repo.MealsRepoIMP;
import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;
import com.example.linguini.HomeScreen.presenter.HomePresenter;
import com.example.linguini.HomeScreen.presenter.HomePresenterIMP;
import com.example.linguini.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeView {
    private static final String TAG = "HomeFragment";
    FirebaseAuth auth;
    FirebaseUser user;
    HomePresenter homePresenter;
    private RecyclerView recyclerView;
    private MealOfDayAdapter mealAdapter;
    private MealCategoryAdapter mealCategoryAdapter;
    private MealAreaAdapter mealAreaAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        user = auth.getCurrentUser();
        String email = user.getEmail();
        Log.i(TAG, "onCreateView: "+ email);

        return view;
    }

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

        List<PojoForMealArea> mealAreas = mealAreaResponse.getMealAreas();

        mealAreaAdapter.updateData(mealAreas);
        mealAreaAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAreaErrorMSG(String error) {
        Log.i(TAG, "showArea: " + error);
    }

    @Override
    public void showMeal(MealResponse mealResponse) {
        Log.i(TAG, "show Meal: " + mealResponse.getMealDay().get(0).getMealName());

        List<PojoForMeal> meals = mealResponse.getMealDay();
        mealAdapter.updateData(meals);
    }

    @Override
    public void showMealErrorMSG(String error) {
        Log.i(TAG, "showArea: " + error);
    }

    @Override
    public void showCategories(MealCategoryResponse mealCategoryResponse) {
        List<PojoForMealCategory> mealCategories = mealCategoryResponse.getMealCategories();
        if (mealCategories != null) {
            mealCategoryAdapter.updateData(mealCategories);
        } else {
            Log.e(TAG, "Meal categories list is null");
        }
    }
    @Override
    public void showCategoriesErrorMSG(String error) {
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView gifImageView = view.findViewById(R.id.gif_image_view);
        gifImageView.setVisibility(View.VISIBLE);

        Glide.with(this).asGif().load(R.drawable.chif_animation).into(gifImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gifImageView.setVisibility(View.GONE);

                homePresenter = new HomePresenterIMP(HomeFragment.this, MealsRepoIMP.getInstance(MealsRemoteDataSourceIMP.getInstance(getActivity())));

                recyclerView = view.findViewById(R.id.recyclerView1);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                mealAreaAdapter = new MealAreaAdapter(new ArrayList<>());
                recyclerView.setAdapter(mealAreaAdapter);

                RecyclerView recyclerView1 = view.findViewById(R.id.recycler_view_card);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
                mealAdapter = new MealOfDayAdapter(new ArrayList<>(), HomeFragment.this);
                recyclerView1.setAdapter(mealAdapter);

                RecyclerView recyclerViewCategory = view.findViewById(R.id.recyclerView2);
                recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
                mealCategoryAdapter = new MealCategoryAdapter(new ArrayList<>());
                recyclerViewCategory.setAdapter(mealCategoryAdapter);

                homePresenter.getIngrediants();
                homePresenter.getArea();
                homePresenter.getMeal();
                homePresenter.getCategory();
            }
        }, 3000);
    }


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        homePresenter = new HomePresenterIMP(this, MealsRepoIMP.getInstance(MealsRemoteDataSourceIMP.getInstance(getActivity())));
//
//        //        btnDeletes = view.findViewById(R.id.btnDeletes);
//        //        btnDeletes.setVisibility(View.GONE);   //TODO important to make it work for UI
//        recyclerView = view.findViewById(R.id.recyclerView1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        mealAreaAdapter = new MealAreaAdapter(new ArrayList<>());
//        recyclerView.setAdapter(mealAreaAdapter);
//
//
//        RecyclerView recyclerView1 = view.findViewById(R.id.recycler_view_card);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
//        mealAdapter = new MealOfDayAdapter(new ArrayList<>(), this);
//        recyclerView1.setAdapter(mealAdapter);
//
//
//        RecyclerView recyclerViewCategory = view.findViewById(R.id.recyclerView2);
//        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
//        mealCategoryAdapter = new MealCategoryAdapter(new ArrayList<>());
//        recyclerViewCategory.setAdapter(mealCategoryAdapter);
//        //Log.i(TAG, "Fetching ingredients data...");
//        homePresenter.getIngrediants();
//        //Log.i(TAG, "Fetching meal areas data...");
//        homePresenter.getArea();
//        //Log.i(TAG, "Fetching meal data...");
//        homePresenter.getMeal();
//        //Log.i(TAG, "Fetching Category data...");
//        homePresenter.getCategory();
//    }

    public void onClicked(PojoForMeal pojoForMeal, View view) {
        Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(pojoForMeal.getIdMeal()));
    }
}