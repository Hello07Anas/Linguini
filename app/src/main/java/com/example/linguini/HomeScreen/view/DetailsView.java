package com.example.linguini.HomeScreen.view;

import android.view.View;

import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;

public interface DetailsView {
    public void showDetailse(MealResponse mealResponse);
    public void showDetailseErrorMSG(String error);
    public void onClicked(PojoForMeal pojoForMeal, View view);
}
