package com.example.caloriestracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_AGE = "USER_AGE";
    public static final String COLUMN_USER_GENDER = "USER_GENDER";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_GOAL = "GOAL";
    public static final String COLUMN_HEIGHT = "USER_HEIGHT";
    public static final String COLUMN_WEIGHT = "USER_WEIGHT";
    public static final String COLUMN_DATE = "LAST_DATE";
    public static final String COLUMN_NEEDS = "CALORIC_NEEDS";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USERS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_AGE +
                " INT, " + COLUMN_USER_GENDER + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_GOAL + " INTEGER, " + COLUMN_HEIGHT + " INTEGER, " + COLUMN_WEIGHT + " INTEGER, " + COLUMN_DATE + " TEXT, " + COLUMN_NEEDS + " INTEGER)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userModel.getUsername());
        cv.put(COLUMN_USER_AGE, userModel.getAge());
        cv.put(COLUMN_USER_GENDER, userModel.getGender());
        cv.put(COLUMN_USER_PASSWORD, userModel.getPassword());
        cv.put(COLUMN_GOAL, userModel.getGoal());
        cv.put(COLUMN_HEIGHT,userModel.getHeight());
        cv.put(COLUMN_WEIGHT,userModel.getWeight());
        cv.put(COLUMN_DATE,userModel.getLastDate());
        cv.put(COLUMN_NEEDS,userModel.getCaloricNeeds());

        long insert = db.insert(USERS_TABLE, null, cv);

        if (insert == -1)
            return false;
         else
            return true;

    }


    public boolean existingValue(String username) {
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count >= 1;
    }

    public boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] whereArgs = {username, password};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count >= 1;
    }

    public void setUserName(String username, String newUserName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, newUserName);
        db.update(USERS_TABLE, cv, "USER_NAME = ?", new String[]{username});
        db.close();
    }

    public void setPassWord(String username, String passWord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_PASSWORD, passWord);
        db.update(USERS_TABLE, cv, "USER_NAME = ?", new String[]{username});
        db.close();
    }

    public void setCaloricNeeds(String username, int needs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NEEDS, needs);
        db.update(USERS_TABLE, cv, "USER_NAME = ?", new String[]{username});
        db.close();
    }

    public void setGoal(String username, int goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GOAL, goal);
        db.update(USERS_TABLE, cv, "USER_NAME = ?", new String[]{username});
        db.close();
    }

    public void setHeight(String username, int height) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_HEIGHT, height);
        db.update(USERS_TABLE, cv, "USER_NAME = ?", new String[]{username});
        db.close();
    }

    public void setWeight(String username, int weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_WEIGHT, weight);
        db.update(USERS_TABLE, cv, "USER_NAME = ?", new String[]{username});
        db.close();
    }

    public void setLastdate(String username, String lastDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, lastDate);
        db.update(USERS_TABLE, cv, "USER_NAME = ?", new String[]{username});
        db.close();
    }

    public int getAge(String username){
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        int age=-1;
        if (cursor.moveToFirst())
            age = cursor.getInt(2);
        cursor.close();
        db.close();
        return  age;
    }

    public String getGender(String username){
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        String gender = "null";
        if (cursor.moveToFirst())
            gender = cursor.getString(3);
        cursor.close();
        db.close();
        return gender;
    }

    public int getCaloricNeeds(String username){
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        int needs = 0;
        if (cursor.moveToFirst())
            needs = cursor.getInt(9);
        cursor.close();
        db.close();
        return needs;
    }

    public int getHeight(String username){
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        int height = 0;
        if (cursor.moveToFirst())
            height = cursor.getInt(6);
        cursor.close();
        db.close();
        return height;
    }

    public int getWeight(String username){
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        int weight = 0;
        if (cursor.moveToFirst())
            weight = cursor.getInt(7);
        cursor.close();
        db.close();
        return weight;
    }

    public int getGoal(String username){
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        int goal = 0;
        if (cursor.moveToFirst())
            goal = cursor.getInt(5);
        cursor.close();
        db.close();
        return goal;
    }

    public String getLastDate(String username){
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_USER_NAME + " = ?";
        String[] whereArgs = {username};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);
        String lastDate = "null";
        if (cursor.moveToFirst())
            lastDate = cursor.getString(8);
        cursor.close();
        db.close();
        return lastDate;
    }

}
