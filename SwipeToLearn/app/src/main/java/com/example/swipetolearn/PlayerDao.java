package com.example.swipetolearn;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//classe qui contient les différentes requêtes qui vont être manipulées dans la BDD
@Dao
public interface PlayerDao {
    //read
    @Query("select * from Player")
    List<Player> getAll();

    @Query("select * from player where login= :login")
    List<Player> getPlayer(String login);

    @Query("SELECT * FROM player where login= :login and password= :password ")
    Player getAPlayer(String login, String password);


    //Create or update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Player> personList);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);
    //update
    @Update
    void updatePlayer(Player player);
    //Delete
    @Delete
    void delete(Player player);


}
