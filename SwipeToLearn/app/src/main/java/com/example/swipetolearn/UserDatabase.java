package com.example.swipetolearn;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//Création de la BDD et déclaration de ses entités
@Database(entities = Player.class, exportSchema = false,version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DB_NAME = "Player_db";
    private static UserDatabase instance;// création d'une instance de la BDD

    public static synchronized UserDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
    public abstract PlayerDao playerDao();
}
