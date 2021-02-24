package com.example.swipetolearn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SwipeToLearnScoresActivity  extends Activity {

    public static TextView zonescore;   //zone de texte où est affiché le score

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout); //Instance du score_layout

        zonescore=(TextView) findViewById(R.id.scoreText);  //Identification de zonescore

    }


    public static void setScoreText(int numberVictory, int swipeNumber){    //Doit mettre à jour le score du joueur
        //zonescore.setText(numberVictory+"");
    }





}
