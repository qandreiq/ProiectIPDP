package com.example.caloriestracker;

public class UserModel {

    private String username;
    private String password;
    private String gender;
    private int age;
    private int id;
    private int goal;
    private int weight;
    private int height;
    private String lastDate;
    private int caloricNeeds;

    public UserModel(int id, String username, String password, int age, String gender, int goal, int height, int weight, String lastDate,int caloricNeeds) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFirstTime(int firstTime) {
        this.goal = firstTime;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public void setCaloricNeeds(int caloricNeeds) {
        this.caloricNeeds = caloricNeeds;
    }
}
