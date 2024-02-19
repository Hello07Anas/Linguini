package com.example.linguini.HomeScreen.view;

import android.view.View;

import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealCategoryResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;

public interface HomeView {
    public void showIngrediants(IngredientsResponse ingredientsResponse);
    public void showIngrediantsErrorMSG(String error);
    // <<<<<>>>>>
    public void showArea(MealAreaResponse mealAreaResponse);
    public void showAreaErrorMSG(String error);
    // <<<<<>>>>>
    public void showMeal(MealResponse mealResponse);
    public void showMealErrorMSG(String error);
    // <<<<<>>>>>
    public void showCategories(MealCategoryResponse mealCategoryResponse);
    public void showCategoriesErrorMSG(String error);
    // <<<<<>>>>>
    void onClicked(PojoForMeal meal, View view);

}
