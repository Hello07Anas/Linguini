package com.example.linguini.HomeScreen.view;

import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;

public interface SearchView {
    void showSearchResults(MealResponse mealResponse);
    void showError(String errorMessage);
}
