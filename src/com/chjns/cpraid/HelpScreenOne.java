package com.chjns.cpraid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.view.KeyEvent;

/**
 * Created by NarineC on 10/22/13.
 */
public class HelpScreenOne extends Activity {

    Intent switchToHelpScreenTwo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.help_screen_one);
        switchToHelpScreenTwo = new Intent(this, HelpScreenTwo.class);
    }

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
                startActivity(switchToHelpScreenTwo);
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

    @Override
    protected void onStop() {

        super.onStop();
    }

}
