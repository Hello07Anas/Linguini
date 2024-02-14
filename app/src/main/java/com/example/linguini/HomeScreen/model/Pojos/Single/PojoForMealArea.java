package com.example.linguini.HomeScreen.model.Pojos.Single;

// TODO link of this POJO " www.themealdb.com/api/json/v1/1/list.php?a=list "
public class PojoForMealArea {
    private String strArea;

    public PojoForMealArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
