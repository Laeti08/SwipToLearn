package com.example.swipetolearn;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SwipeToLearnActivity extends AppCompatActivity {
    private SwipeGestureDetector gestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        gestureDetector=new SwipeGestureDetector(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void onSwipe(SwipeGestureDetector.SwipeDirection direction) {
        String message="";
        switch (direction){
            case LEFT_TO_RIGHT:
                message="Left to right swipe";
                break;
            case RIGHT_TO_LEFT:
                message="Right to left swipe";
                break;
        }
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
}
