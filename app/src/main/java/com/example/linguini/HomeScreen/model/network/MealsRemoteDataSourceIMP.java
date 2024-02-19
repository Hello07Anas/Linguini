package com.example.linguini.HomeScreen.model.network;

import android.content.Context;
import android.util.Log;

import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealCategoryResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceIMP implements MealsRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "MealsRemoteDataSourceIMP";
    private MealService service;
    private static MealsRemoteDataSourceIMP instance;

    private MealsRemoteDataSourceIMP(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service = retrofit.create(MealService.class);
    }

    // Singleton design pattern
    public static synchronized MealsRemoteDataSourceIMP getInstance(Context context) {
        if (instance == null) {
            instance = new MealsRemoteDataSourceIMP(context);
        }
        return instance;
    }

    @Override
    public void getIngredients(NetworkCallBack.IngredientsCallBack ingredientsCallBack) {
        Call<IngredientsResponse> call = service.getIngredients();

        call.enqueue(new Callback<IngredientsResponse>() {
            @Override
            public void onResponse(Call<IngredientsResponse> call, Response<IngredientsResponse> response) {
                ingredientsCallBack.onSuccessIngredients(response.body());
            }

            @Override
            public void onFailure(Call<IngredientsResponse> call, Throwable t) {
                ingredientsCallBack.onFailIngredients(t.getMessage());
            }
        });
    }

    @Override
    public void getArea(NetworkCallBack.AreaCallBack areaCallBack) {
        Call<MealAreaResponse> call = service.getMealAreas();

        call.enqueue(new Callback<MealAreaResponse>() {
            @Override
            public void onResponse(Call<MealAreaResponse> call, Response<MealAreaResponse> response) {
                areaCallBack.onSuccessArea(response.body());
            }

            @Override
            public void onFailure(Call<MealAreaResponse> call, Throwable t) {
                areaCallBack.onFailArea(t.getMessage());
            }
        });
    }

    @Override
    public void getMeal(NetworkCallBack.MealCallBack mealCallBack) {
        Call<MealResponse> call = service.getMealOfDay();

        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                mealCallBack.onSuccessMeal(response.body());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                mealCallBack.onFailMeal(t.getMessage());
            }
        });
    }

    @Override
    public void getMealDetails(NetworkCallBack.MealCallBackDetails mealCallBackDetails, String id) {
        Call<MealResponse> call = service.getMealDetails(id);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i("TAG", "onResponse: "+ response.body().getMealDay().get(0).getMealName());
                mealCallBackDetails.onSuccessMeal(response.body());
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                mealCallBackDetails.onFailMeal(t.getMessage());
            }
        });
    }

    @Override
    public void getMealCategories(NetworkCallBack.CategoriesCallBack categoriesCallBack) {
        Call<MealCategoryResponse> call = service.getMealCategories();
        call.enqueue(new Callback<MealCategoryResponse>() {
            @Override
            public void onResponse(Call<MealCategoryResponse> call, Response<MealCategoryResponse> response) {
                categoriesCallBack.onSuccessCategories(response.body());
            }

            @Override
            public void onFailure(Call<MealCategoryResponse> call, Throwable t) {
                categoriesCallBack.onFailCategories(t.getMessage());
            }
        });
    }


    @Override
    public void getMealSearch(String query, NetworkCallBack.MealCallBackSearch mealCallBackSearch) {
        Single<MealResponse> mealsByCategoryCall = service.searchByName(query);

        mealsByCategoryCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    mealCallBackSearch.onSuccessSearch(response);
                    Log.i(TAG, "getMealSearch: " + response.getMealDay().get(0).getMealName() + "LOL");
                }, error ->
                        mealCallBackSearch.onFailSearch("Network request failed. " +
                                "Error: " + error.getMessage()));
//        service.searchByName(query)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<MealResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onSuccess(MealResponse mealResponse) {
//                        mealCallBackSearch.onSuccessSearch(mealResponse);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mealCallBackSearch.onFailSearch(e.getMessage());
//                    }
//                });
    }
}
