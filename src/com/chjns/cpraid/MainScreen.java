package com.chjns.cpraid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.KeyEvent;

public class MainScreen extends Activity {
    //private GestureDetector mGestureDetector;
    Intent switchToMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen);
        switchToMainActivity = new Intent(this, MainActivity.class);
        //mGestureDetector = new GestureDetector(this, new Controls());
    }
//    @Override
//    public boolean onGenericMotionEvent(MotionEvent event) {
//        mGestureDetector.onTouchEvent(event);
//        return true;
//    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
                return true;
            case KeyEvent.KEYCODE_ENTER:
                return true;
            default:
                startActivity(switchToMainActivity);
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

