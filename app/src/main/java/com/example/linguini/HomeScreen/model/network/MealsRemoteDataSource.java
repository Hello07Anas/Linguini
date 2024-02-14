package com.example.linguini.HomeScreen.model.network;

public interface MealsRemoteDataSource {
    public void getIngredients(NetworkCallBack.IngredientsCallBack ingredientsCallBack);

    public void getArea(NetworkCallBack.AreaCallBack areaCallBack);

    public void getMeal(NetworkCallBack.MealCallBack mealCallBack);
}
