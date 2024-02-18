package com.example.linguini.HomeScreen.model.repo;

import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSource;
import com.example.linguini.HomeScreen.model.network.NetworkCallBack;

import io.reactivex.rxjava3.core.Completable;

public class MealsRepoIMP implements MealsRepository {

    private static MealsRepoIMP instance = null;
    private MealsRemoteDataSource remoteDataSource;

    private MealsRepoIMP(MealsRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static MealsRepoIMP getInstance(MealsRemoteDataSource mealsRemoteDataSource) {
        if (instance == null) {
            instance = new MealsRepoIMP(mealsRemoteDataSource);
        }
        return instance;
    }

    @Override
    public void getIngediants(NetworkCallBack.IngredientsCallBack ingredientsCallBack) {
        remoteDataSource.getIngredients(ingredientsCallBack);
    }

    @Override
    public void getArea(NetworkCallBack.AreaCallBack areaCallBack) {
        remoteDataSource.getArea(areaCallBack);
    }

    public void getMeal(NetworkCallBack.MealCallBack mealCallBack) {
        remoteDataSource.getMeal(mealCallBack);
    }

    @Override
    public void getMealDetails(NetworkCallBack.MealCallBackDetails mealCallBackDetails, String id) {
        remoteDataSource.getMealDetails(mealCallBackDetails, id);
    }

    @Override
    public void getCategorys(NetworkCallBack.CategoriesCallBack categoriesCallBack) {
        remoteDataSource.getMealCategories(categoriesCallBack);
    }

    @Override
    public void getSearchByName(String query, NetworkCallBack.MealCallBackSearch mealCallBackSearch) {
        remoteDataSource.getMealSearch(query, mealCallBackSearch);
    }

    @Override
    public Completable addToFavorites(String mealId) {
        // Implement adding to favorites
        return Completable.complete(); // Completable from RxJava
    }
}


//package com.example.linguini.HomeScreen.model.repo;
//
//import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSource;
//import com.example.linguini.HomeScreen.model.network.MealsRemoteDataSourceIMP;
//import com.example.linguini.HomeScreen.model.network.NetworkCallBack;
//
//import io.reactivex.rxjava3.core.Completable;
//
//public class MealsRepoIMP implements MealsRepository{
//
//    private static MealsRepoIMP instance = null;
//    private MealsRemoteDataSource remoteDataSource;
//
//
//    // all needed operation will be here ' I think so '
//
//
//    private MealsRepoIMP(MealsRemoteDataSource remoteDataSource) {
//        this.remoteDataSource = remoteDataSource;
//    }
//
//    public static MealsRepoIMP getInstance(MealsRemoteDataSourceIMP mealsRemoteDataSourceIMP) {
//        if(instance == null) {
//            instance = new MealsRepoIMP(mealsRemoteDataSourceIMP);
//        }
//        return instance;
//    }
//
//    @Override
//    public void getIngediants(NetworkCallBack.IngredientsCallBack ingredientsCallBack) {
//        remoteDataSource.getIngredients(ingredientsCallBack);
//    }
//
//    @Override
//    public void getArea(NetworkCallBack.AreaCallBack areaCallBack) {
//        remoteDataSource.getArea(areaCallBack);
//    }
//
//    public void getMeal(NetworkCallBack.MealCallBack mealCallBack) {
//        remoteDataSource.getMeal(mealCallBack);
//    }
//
//    @Override
//    public void getMealDetails(NetworkCallBack.MealCallBackDetails mealCallBackDetails, String id) {
//        remoteDataSource.getMealDetails(mealCallBackDetails, id);
//    }
//
//    @Override
//    public void getCategorys(NetworkCallBack.CategoriesCallBack categoriesCallBack) {
//        remoteDataSource.getMealCategories(categoriesCallBack);
//    }
//
//    //TODO <><><><><><><><><>
//    @Override
//    public void getSearchByName(String query, NetworkCallBack.MealCallBackSearch mealCallBackSearch) {
//        remoteDataSource.getMealSearch(query, mealCallBackSearch);
//    }
//
//
//    @Override
//    public Completable addToFavorites(String mealId) {
//        return null;
//    }
//}
