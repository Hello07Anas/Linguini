package com.example.linguini.HomeScreen.model.repo;

import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSource;
import com.example.linguini.HomeScreen.model.network.NetworkCallBack;

public interface MealsRepository {

    public void getIngediants(NetworkCallBack.IngredientsCallBack ingredientsCallBack);

    public void getArea(NetworkCallBack.AreaCallBack areaCallBack);

    public void getMeal(NetworkCallBack.MealCallBack mealCallBack);

    public void getMealDetails(NetworkCallBack.MealCallBackDetails mealCallBackDetails, String id);
}
