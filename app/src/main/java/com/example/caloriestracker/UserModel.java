package com.example.caloriestracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserModel {

    @ColumnInfo(name = "user_name")
    private String username;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "age")
    private int age;
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "goal")
    private int goal;
    @ColumnInfo(name = "weight")
    private int weight;
    @ColumnInfo(name = "height")
    private int height;
    @ColumnInfo(name = "lastDate")
    private String lastDate;
    @ColumnInfo(name = "caloricNeeds")
    private int caloricNeeds;

    public UserModel( String username, String password, int age, String gender, int goal, int height, int weight, String lastDate,int caloricNeeds) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.goal = goal;
        this.height = height;
        this.weight = weight;
        this.lastDate = lastDate;
        this.caloricNeeds = caloricNeeds;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public int getGoal() {
        return goal;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getLastDate() {
        return lastDate;
    }

    public int getCaloricNeeds() {
        return caloricNeeds;
    }

    public void setId(int id) {
        this.id = id;
    }
}
//ORM