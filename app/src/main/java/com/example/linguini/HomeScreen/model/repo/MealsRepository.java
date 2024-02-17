package com.example.linguini.HomeScreen.model.repo;

import com.example.linguini.HomeScreen.model.network.NetworkCallBack;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Completable;

public interface MealsRepository {

    void getIngediants(NetworkCallBack.IngredientsCallBack ingredientsCallBack);

    void getArea(NetworkCallBack.AreaCallBack areaCallBack);

    void getMeal(NetworkCallBack.MealCallBack mealCallBack);

    void getMealDetails(NetworkCallBack.MealCallBackDetails mealCallBackDetails, String id);

    Completable addToFavorites(String mealId);

}
