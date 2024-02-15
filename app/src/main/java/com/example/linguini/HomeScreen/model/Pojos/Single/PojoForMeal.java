package com.example.linguini.HomeScreen.model.Pojos.Single;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PojoForMeal {
    private String idMeal;
    @SerializedName("strMeal")
    private String mealName;
    private String strCategory;
    @SerializedName("strArea")
    private String areaOfMeal;
    private String strInstructions;
    @SerializedName("strMealThumb")
    private String imageUrl;
    private String strYoutube;
    private List<String> ingredients;
    private List<String> measures;

    public PojoForMeal(String idMeal, String strMeal, String strCategory, String strArea,
                       String strInstructions, String strMealThumb, String strYoutube,
                       List<String> ingredients, List<String> measures) {
        this.idMeal = idMeal;
        this.mealName = strMeal;
        this.strCategory = strCategory;
        this.areaOfMeal = strArea;
        this.strInstructions = strInstructions;
        this.imageUrl = strMealThumb;
        this.strYoutube = strYoutube;
        this.ingredients = ingredients;
        this.measures = measures;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getAreaOfMeal() {
        return areaOfMeal;
    }

    public void setAreaOfMeal(String areaOfMeal) {
        this.areaOfMeal = areaOfMeal;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getMeasures() {
        return measures;
    }

    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }
}
