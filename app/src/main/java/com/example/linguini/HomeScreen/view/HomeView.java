package com.example.linguini.HomeScreen.view;

import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;

public interface HomeView {
    public void showIngrediants(IngredientsResponse ingredientsResponse);
    public void showIngrediantsErrorMSG(String error);



    public void showArea(MealAreaResponse mealAreaResponse);
    public void showAreaErrorMSG(String error);


    public void showMeal(MealResponse mealResponse);
    public void showMealErrorMSG(String error);

}
