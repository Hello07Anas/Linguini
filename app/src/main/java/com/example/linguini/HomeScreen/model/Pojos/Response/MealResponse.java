package com.example.linguini.HomeScreen.model.Pojos.Response;

import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {

    @SerializedName("meals")
    private List<PojoForMeal> mealDay;

    public List<PojoForMeal> getMealDay() {
        return mealDay;
    }

    public void setMealDay(List<PojoForMeal> mealDay) {
        this.mealDay = mealDay;
    }

}