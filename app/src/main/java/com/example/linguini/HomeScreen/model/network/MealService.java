package com.example.linguini.HomeScreen.model.network;

import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealCategoryResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("list.php?i=list")
    Call<IngredientsResponse> getIngredients();

    @GET("list.php?a=list")
    Call<MealAreaResponse> getMealAreas();

    @GET("categories.php")
    Call<MealCategoryResponse> getMealCategories();

    @GET("random.php")
    Call<MealResponse> getMealOfDay();

    @GET("lookup.php?i=")
    Call<MealResponse> getMealDetails(@Query("i") String mealId);

    @GET("search.php")
    Single<MealResponse> searchByName(@Query("s") String mealName);

}
