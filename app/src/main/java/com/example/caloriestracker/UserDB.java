package com.example.caloriestracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserModel.class}, version = 1)
public abstract class UserDB extends RoomDatabase {

    public abstract UserDAO userDAO();

    private static UserDB INSTANCE;

    public static UserDB getDBInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "USER_DATABASE")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
//singleton
//initializeaza baza de date