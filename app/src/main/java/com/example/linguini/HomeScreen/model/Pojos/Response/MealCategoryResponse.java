package com.example.linguini.HomeScreen.model.Pojos.Response;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealCategory;

import java.util.List;

public class MealCategoryResponse {
    private List<PojoForMealCategory> mealCategories;

    public List<PojoForMealCategory> getMealCategories() {
        return mealCategories;
    }

    public void setMealCategories(List<PojoForMealCategory> mealCategories) {
        this.mealCategories = mealCategories;
    }
}