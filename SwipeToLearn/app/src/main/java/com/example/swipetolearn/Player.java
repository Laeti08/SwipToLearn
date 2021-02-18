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
    private String Login;
    @ColumnInfo
    private String Password;
    @ColumnInfo
    private int score;



    public Player(int idPlayer, String login, String password, int score) {
        this.idPlayer = idPlayer;
        Login = login;
        Password = password;
        this.score = score;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
