package com.example.linguini.HomeScreen.model.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;



@Dao
public interface MealDAO {
    @Insert
    Completable insertMeal(PojoForMeal pojoForMeal);
    @Delete
    void deleteMeal(PojoForMeal pojoForMeal);
    @Query("SELECT * FROM meals_db")
    Observable<List<PojoForMeal>> getMeals();

    @Query("SELECT * FROM meals_db WHERE idMeal = :mealId")
    PojoForMeal getMealById(String mealId);

}
