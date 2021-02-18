package com.example.swipetolearn;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDao {
    //read
    @Query("select * from Player")
    List<Player> getAll();

    //Create or update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Player> personList);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);
    //Delete
    @Delete
    void delete(Player player);


}
