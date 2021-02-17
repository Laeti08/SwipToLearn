package com.example.swipetolearn;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SwipeToLearnApplication.class, SwipeToLearnLoginActivity.class,SwipeToLearnActivity.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract SwipeToLearnActivityDao swipeToLearnActivityDao();
    public abstract SwipeToLearnApplicationDao swipeToLearnApplicationDao();
    public abstract SwipeToLearnLoginActivityDao swipeToLearnLoginActivityDao();

}
