package com.example.caloriestracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM usermodel")
    List<UserModel> getAllUsers();

    @Query("SELECT user_name FROM usermodel")
    List<String> getAllExistingUserNames();

    @Query("SELECT password FROM usermodel WHERE user_name =:userName")
    String getPassword(String userName);

    @Query("SELECT gender FROM usermodel WHERE user_name =:userName")
    String getGender(String userName);

    @Query("UPDATE usermodel SET goal=:newGoal WHERE user_name =:userName")
    int setGoal(int newGoal, String userName);

    @Query("UPDATE usermodel SET height=:newHeight WHERE user_name =:userName")
    int setHeight(int newHeight, String userName);

    @Query("UPDATE usermodel SET weight=:newWeight WHERE user_name =:userName")
    int setWeight(int newWeight, String userName);

    @Query("UPDATE usermodel SET caloricNeeds=:newNeeds WHERE user_name =:userName")
    int setNeeds(int newNeeds, String userName);

    @Query("SELECT age FROM usermodel WHERE user_name =:userName")
    int getAge(String userName);

    @Query("SELECT height FROM usermodel WHERE user_name =:userName")
    int getHeight(String userName);

    @Query("SELECT weight FROM usermodel WHERE user_name =:userName")
    int getWeight(String userName);

    @Query("SELECT goal FROM usermodel WHERE user_name =:userName")
    int getGoal(String userName);

    @Insert
    void insertUser(UserModel...users);

    @Delete
    void delete(UserModel user);
}
