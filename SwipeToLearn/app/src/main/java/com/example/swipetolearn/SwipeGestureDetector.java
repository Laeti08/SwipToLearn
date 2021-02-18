package com.example.swipetolearn;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SwipeGestureDetector  extends GestureDetector {

    private final static int DELTA_MIN=50;

    public SwipeGestureDetector(final SwipeToLearnActivity context ){
        super(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("DEBUG", e1 + " - " + e2);

                float deltaX = e2.getX()-e1.getX();

                if(Math.abs(deltaX)>=DELTA_MIN){
                    if(deltaX<0){
                        context.onSwipe(SwipeDirection.RIGHT_TO_LEFT);
                        return true;
                    }
                    else{
                        context.onSwipe(SwipeDirection.LEFT_TO_RIGHT);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public static enum SwipeDirection{
        LEFT_TO_RIGHT,RIGHT_TO_LEFT
    }
}
