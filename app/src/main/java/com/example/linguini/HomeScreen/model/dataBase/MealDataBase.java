package com.example.linguini.HomeScreen.model.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.linguini.HomeScreen.model.Pojos.Single.PojoForMeal;

@Database(entities = PojoForMeal.class, version = 1)
public abstract class MealDataBase extends RoomDatabase {

    private static MealDataBase instance; // static <> to implement SingleTone pattern
    public abstract MealDAO mealDAO();

    // to avoid more than thread access DB and avoid type on the same row
    public static synchronized MealDataBase getInstance(Context context){
        //singleTone pattern
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                     MealDataBase.class, "meal_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
