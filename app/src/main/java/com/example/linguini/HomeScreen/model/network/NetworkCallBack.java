package com.example.linguini.HomeScreen.model.network;

import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealCategoryResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForIngredients;

public interface NetworkCallBack {

    interface IngredientsCallBack {
        public void onSuccessIngredients(IngredientsResponse ingredientsResponse);
        public void onFailIngredients(String errorMsg);
    }

    interface AreaCallBack {
        public void onSuccessArea(MealAreaResponse mealAreaResponse);
        public void onFailArea(String error);
    }

    interface MealCallBack{
        public void onSuccessMeal(MealResponse mealResponse);
        public void onFailMeal(String error);
    }

    interface MealCallBackDetails {
        public void onSuccessMeal(MealResponse mealResponse);
        public void onFailMeal(String error);
    }

    interface MealCallBackSearch {
        public void onSuccessSearch(MealResponse mealResponse);
        public void onFailSearch(String error);
    }

    interface CategoriesCallBack{
        public void onSuccessCategories(MealCategoryResponse mealCategoryResponse);
        public void onFailCategories(String error);
    }

}
