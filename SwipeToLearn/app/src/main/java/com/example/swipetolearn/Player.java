package com.example.swipetolearn;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Player {
    @PrimaryKey(autoGenerate = true)
    private int idPlayer;
    @ColumnInfo
    private String login;
    @ColumnInfo
    private String password;
    @ColumnInfo
    private int score;

// classe du joueur avec ses différents attributs

    public Player(int idPlayer, String login, String password, int score) {
        this.idPlayer = idPlayer;
        this.login = login;
        this.password = password;
        this.score = score;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
