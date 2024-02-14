package com.example.linguini.HomeScreen.model.Pojos.Response;
import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForIngredients;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientsResponse {

    @SerializedName("meals")
    private List<PojoForIngredients> ingredients;

    public List<PojoForIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<PojoForIngredients> ingredients) {
        this.ingredients = ingredients;
    }
}