package com.example.caloriestracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseEatenFood extends SQLiteOpenHelper {

    public static final String EATEN_FOOD_TABLE = "EATEN_FOOD";
    public static final String COLUMN_USER = "USER_NAME";
    public static final String COLUMN_FOOD = "FOOD_NAME";
    public static final String COLUMN_CALORIES = "CALORIES";
    public static final String COLUMN_DATE = "DATE";

    public DataBaseEatenFood(@Nullable Context context) {
        super(context, "eaten_food.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + EATEN_FOOD_TABLE + " (" + COLUMN_USER + " TEXT, " + COLUMN_FOOD + " TEXT, " + COLUMN_CALORIES + " INTEGER, " + COLUMN_DATE + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(String user, FoodModel food, String date,int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        double caloriesToAdd;
        double foodCalories = food.getKcalories();
        caloriesToAdd = foodCalories*quantity/100;
        cv.put(COLUMN_USER, user);
        cv.put(COLUMN_FOOD, food.getName());
        cv.put(COLUMN_CALORIES, (int)caloriesToAdd);
        cv.put(COLUMN_DATE, date);

        long insert = db.insert(EATEN_FOOD_TABLE, null, cv);

        if (insert == -1)
            return false;
        else
            return true;
    }

    public int getEatenCalories(String username, String date) {
        String query = "SELECT SUM( "+ COLUMN_CALORIES+ " ) as Total FROM "+EATEN_FOOD_TABLE + " WHERE " + COLUMN_USER + " = ? AND " + COLUMN_DATE + " = ?";
        String[] whereArgs = {username, date};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        int eatenCalories=0;
        if (cursor.moveToFirst())
            eatenCalories = cursor.getInt(0);
        cursor.close();
        db.close();
        return eatenCalories;
    }

    public List<FoodModel> getEverything(String username, String date) {
        List<FoodModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + EATEN_FOOD_TABLE + " WHERE " + COLUMN_USER + " = ? AND " + COLUMN_DATE + " = ?";
        String[] whereArgs = {username, date};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, whereArgs);

        if (cursor.moveToFirst()) {
            do {
                String foodName = cursor.getString(1);
                int foodCalories = cursor.getInt(2);

                FoodModel newFood = new FoodModel(foodName,foodCalories);
                returnList.add(newFood);

            } while (cursor.moveToNext());
        } else {}
        cursor.close();
        db.close();
        return returnList;
    }

}
