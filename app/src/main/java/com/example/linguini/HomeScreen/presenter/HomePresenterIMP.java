package com.example.linguini.HomeScreen.presenter;

import com.example.linguini.HomeScreen.model.Pojos.Response.MealCategoryResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealResponse;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealArea;
import com.example.linguini.HomeScreen.model.repo.MealsRepository;
import com.example.linguini.HomeScreen.model.Pojos.Response.IngredientsResponse;
import com.example.linguini.HomeScreen.model.Pojos.Response.MealAreaResponse;
import com.example.linguini.HomeScreen.model.network.NetworkCallBack;
import com.example.linguini.HomeScreen.view.HomeView;

import java.util.List;

public class HomePresenterIMP implements HomePresenter, NetworkCallBack.IngredientsCallBack, NetworkCallBack.AreaCallBack, NetworkCallBack.MealCallBack, NetworkCallBack.MealCallBackDetails, NetworkCallBack.CategoriesCallBack {

    private HomeView homeView; // refrance from view to connect view with model
    private MealsRepository mealsRepository;  // refrance from model to connect view with view

    @Override
    public void getIngrediants() {
        mealsRepository.getIngediants(this);
    }

    @Override
    public void getArea() {
        mealsRepository.getArea(this);
    }

    @Override
    public void getMeal() { mealsRepository.getMeal(this);}

    @Override
    public void getCategory() {
        mealsRepository.getCategorys(this);
    }

    @Override
    public void showArea(List<PojoForMealArea> areas) {
        // Create a new instance of MealAreaResponse and populate it with the provided list
        MealAreaResponse mealAreaResponse = new MealAreaResponse();
        mealAreaResponse.setMealAreas(areas);

        // Pass the created MealAreaResponse object to the view
        homeView.showArea(mealAreaResponse);
    }

    @Override
    public void showAreaErrorMSG(String error) {

    }

    public HomePresenterIMP(HomeView homeView, MealsRepository mealsRepository) {
        this.homeView = homeView;
        this.mealsRepository = mealsRepository;
    }

    @Override
    public void onSuccessIngredients(IngredientsResponse ingredientsResponse) {
        homeView.showIngrediants(ingredientsResponse);
    }
    public void onFailIngredients(String errorMsg) {
        homeView.showIngrediantsErrorMSG(errorMsg);
    }

    @Override
    public void onSuccessArea(MealAreaResponse mealAreaResponse) {
        homeView.showArea(mealAreaResponse);
    }
    @Override
    public void onFailArea(String error) {
        homeView.showAreaErrorMSG(error);
    }

    public void onSuccessMeal(MealResponse mealResponse) {
        homeView.showMeal(mealResponse);
    }
    @Override
    public void onFailMeal(String error) {
        homeView.showMealErrorMSG(error);
    }

    @Override
    public void onSuccessCategories(MealCategoryResponse mealCategoryResponse) {
        homeView.showCategories(mealCategoryResponse);
    }

    @Override
    public void onFailCategories(String error) {
        homeView.showCategoriesErrorMSG(error);
    }
}
