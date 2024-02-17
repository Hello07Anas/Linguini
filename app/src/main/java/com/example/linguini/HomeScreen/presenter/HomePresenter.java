package com.example.linguini.HomeScreen.presenter;

import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMealArea;

import java.util.List;

public interface HomePresenter {

    public void getIngrediants();

    public void getArea();

    public void getMeal();

    public void getCategory();

    void showArea(List<PojoForMealArea> areas);
    void showAreaErrorMSG(String error);

}
