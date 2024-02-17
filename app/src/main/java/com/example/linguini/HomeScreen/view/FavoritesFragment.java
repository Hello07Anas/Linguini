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
import android.widget.ImageButton;

import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.example.linguini.HomeScreen.model.dataBase.MealDataBase;
import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;
import com.example.linguini.HomeScreen.model.repo.MealsRepoIMP;
import com.example.linguini.HomeScreen.presenter.HomePresenter;
import com.example.linguini.HomeScreen.presenter.HomePresenterIMP;
import com.example.linguini.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesFragment extends Fragment implements HomeView {

    private RecyclerView recyclerView;
    private MealOfDayAdapter mealOfDayAdapter;
    private HomePresenter homePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_card_fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mealOfDayAdapter = new MealOfDayAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(mealOfDayAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter = new HomePresenterIMP(this, MealsRepoIMP.getInstance(MealsRemoteDataSourceIMP.getInstance(getActivity())));

        MealDataBase mealDataBase = MealDataBase.getInstance(getContext());
        mealDataBase.mealDAO().getMeals()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PojoForMeal>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull List<PojoForMeal> pojoForMeals) {
                        mealOfDayAdapter.updateData(pojoForMeals);
                        mealOfDayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });

        homePresenter.getMeal();

        mealOfDayAdapter.setOnDeleteClickListener(new MealOfDayAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteMeal(position);
            }
        });
    }

    private void deleteMeal(int position) {
        List<PojoForMeal> meals = mealOfDayAdapter.getData();
        if (position < 0 || position >= meals.size()) {
            return;
        }

        PojoForMeal meal = meals.get(position);

        // Perform the database operation asynchronously using RxJava
        Single.fromCallable(() -> {
                    MealDataBase.getInstance(getContext()).mealDAO().deleteMeal(meal);
                    return meal;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deletedMeal -> {
                    // Remove item from the list
                    meals.remove(position);
                    // Notify adapter of the item removal
                    mealOfDayAdapter.notifyItemRemoved(position);
                }, throwable -> {
                    // Handle error
                    throwable.printStackTrace();
                });
    }



    @Override
    public void showIngrediants(IngredientsResponse ingredientsResponse) {}

    @Override
    public void showIngrediantsErrorMSG(String error) {}

    @Override
    public void showArea(MealAreaResponse mealAreaResponse) {}

    @Override
    public void showAreaErrorMSG(String error) {}

    @Override
    public void showMeal(MealResponse mealResponse) {}

    @Override
    public void showMealErrorMSG(String error) {}

    @Override
    public void onClicked(PojoForMeal meal, View view) {}
}

//package com.example.linguini.HomeScreen.view;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
//import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
//import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
//import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
//import com.example.linguini.HomeScreen.model.dataBase.MealDataBase;
//import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;
//import com.example.linguini.HomeScreen.model.repo.MealsRepoIMP;
//import com.example.linguini.HomeScreen.presenter.HomePresenter;
//import com.example.linguini.HomeScreen.presenter.HomePresenterIMP;
//import com.example.linguini.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.core.Observer;
//import io.reactivex.rxjava3.core.SingleObserver;
//import io.reactivex.rxjava3.disposables.Disposable;
//import io.reactivex.rxjava3.schedulers.Schedulers;
//
//
//public class FavoritesFragment extends Fragment implements HomeView {
//
//    private RecyclerView recyclerView;
//    private MealOfDayAdapter mealOfDayAdapter;
//    HomePresenter homePresenter;
//    ImageButton btnDeletes;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view =  inflater.inflate(R.layout.fragment_favorites, container, false);
//
//        recyclerView = view.findViewById(R.id.recycler_view_card_fav);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mealOfDayAdapter = new MealOfDayAdapter(new ArrayList<>(), this);
//        recyclerView.setAdapter(mealOfDayAdapter);
//
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        homePresenter = new HomePresenterIMP(this, MealsRepoIMP.getInstance(MealsRemoteDataSourceIMP.getInstance(getActivity())));
//        // DB
//        MealDataBase mealDataBase = MealDataBase.getInstance(getContext());
//
//            mealDataBase.mealDAO().getMeals()
//                    .subscribeOn(Schedulers.computation())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<List<PojoForMeal>>() {
//                        @Override
//                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<PojoForMeal> pojoForMeals) {
//                            mealOfDayAdapter.updateData(pojoForMeals);
//                            mealOfDayAdapter.notifyDataSetChanged();
//                        }
//
//                        @Override
//                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//        homePresenter.getMeal();
//    }
//
//
//    @Override
//    public void showIngrediants(IngredientsResponse ingredientsResponse) {
//
//    }
//
//    @Override
//    public void showIngrediantsErrorMSG(String error) {
//
//    }
//
//    @Override
//    public void showArea(MealAreaResponse mealAreaResponse) {
//
//    }
//
//    @Override
//    public void showAreaErrorMSG(String error) {
//
//    }
//
//    @Override
//    public void showMeal(MealResponse mealResponse) {
//        List<PojoForMeal> meals = mealResponse.getMealDay();
//    }
//
//    @Override
//    public void showMealErrorMSG(String error) {
//
//    }
//
//    @Override
//    public void onClicked(PojoForMeal meal, View view) {
//
//    }
//}