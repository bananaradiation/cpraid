package com.chjns.cpraid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.KeyEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainScreen extends Activity {
    private GestureDetector mGestureDetector; //for implementing gestures to work with Glass
    //private MotionEvent mGetEvent;
    Intent switchToMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen);
        switchToMainActivity = new Intent(this, MainActivity.class);
        mGestureDetector = new GestureDetector(this, new Controls());
    }
//    @Override
//    public boolean onGenericMotionEvent(MotionEvent event) {
//        mGestureDetector.onTouchEvent(event);
//        return true;
//    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            // Handle tap events.
            case KeyEvent.KEYCODE_DPAD_CENTER:
              //  startActivity(new Intent(this, MainActivity.class));
                //  count = 5000;
            case KeyEvent.KEYCODE_ENTER:
                //count = 0000;
              //  setContentView(R.layout.activity_main);
                //  toggleStopWatch();
                return true;
            default:
                startActivity(switchToMainActivity);
                //setContentView(R.layout.activity_main);
                return super.onKeyDown(keyCode, event);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

