package com.example.linguini.HomeScreen.presenter;

import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.network.NetworkCallBack;
import com.example.linguini.HomeScreen.model.repo.MealsRepository;
import com.example.linguini.HomeScreen.view.DetailsView;

public class DetailsPresenterIMP implements DetailsPresenter, NetworkCallBack.MealCallBackDetails {
    private DetailsView detailsView;
    private MealsRepository mealsRepository;
    public DetailsPresenterIMP(DetailsView detailsView, MealsRepository mealsRepository) {
        this.detailsView = detailsView;
        this.mealsRepository = mealsRepository;
    }
    @Override
    public void getMealDetails(String id) {
        mealsRepository.getMealDetails(this, id);
    }
    @Override
    public void onSuccessMeal(MealResponse mealResponse) {
        detailsView.showDetailse(mealResponse);
    }
    @Override
    public void onFailMeal(String error) {
        detailsView.showDetailseErrorMSG(error);
    }
}