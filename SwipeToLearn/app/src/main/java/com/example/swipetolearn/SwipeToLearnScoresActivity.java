package com.example.swipetolearn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SwipeToLearnScoresActivity  extends Activity {

    public static TextView zonescore;//VERSION NULL

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);

        //VERSION NULLzonescore=(TextView) findViewById(R.id.scoreText);

    }


    public static void setScoreText(int numberVictory, int swipeNumber){//VERSION NULL
        zonescore.setText(numberVictory+"");
    }





}
