package com.example.linguini.HomeScreen.model.Pojos.Response;

import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealArea;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealAreaResponse {
    @SerializedName("meals")
    private List<PojoForMealArea> mealAreas;

    public List<PojoForMealArea> getMealAreas() {
        return mealAreas;
    }

    public void setMealAreas(List<PojoForMealArea> mealAreas) {
        this.mealAreas = mealAreas;
    }
}