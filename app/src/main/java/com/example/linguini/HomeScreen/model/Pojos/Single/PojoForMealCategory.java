package com.example.linguini.HomeScreen.model.Pojos.Single;


// TODO link for this POJO "www.themealdb.com/api/json/v1/1/list.php?c=list"
public class PojoForMealCategory {
    private String strCategory;

    public PojoForMealCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
}
