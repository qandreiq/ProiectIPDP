package com.example.caloriestracker;

public class FoodModel implements Cloneable {
    private String name;
    private int kcalories;

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public FoodModel(String name, int kcalories) {
        this.name = name;
        this.kcalories = kcalories;
    }

    public String getName() {
        return name;
    }

    public int getKcalories() {
        return kcalories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKcalories(int kcalories) {
        this.kcalories = kcalories;
    }

    @Override
    public String toString() {
        return "Food: " + name + ", " + kcalories + " kcal";
    }
}
