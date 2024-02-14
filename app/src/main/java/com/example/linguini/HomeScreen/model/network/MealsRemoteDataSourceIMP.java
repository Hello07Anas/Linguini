package com.example.linguini.HomeScreen.model.network;

import android.content.Context;

import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceIMP implements MealsRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static Retrofit retrofit;
    private MealService service;
    private static MealsRemoteDataSourceIMP instance;



    private MealsRemoteDataSourceIMP(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MealService.class);
    }


    // singeltone desgin pattern <<
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
}
