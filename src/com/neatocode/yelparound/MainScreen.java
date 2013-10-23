package com.neatocode.yelparound;

import android.app.Activity;
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.widget.Chronometer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by NarineC on 10/22/13.
 */
public class MainScreen extends Activity {
   // private GestureDetector mGestureDetector; //for implementing gestures to work with Glass
    //private MotionEvent mGetEvent;


  //  controlls getControll = new controlls();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  mGestureDetector = new GestureDetector(this, new controlls());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen);

       // checkGesture();
     //  if(getControll.onSingleTapUp(mGetEvent))
       // {
      //      setContentView(R.layout.activity_main);
      ///  }

    }


   /// @Override
   // public boolean onGenericMotionEvent(MotionEvent event) {
   //     mGestureDetector.onTouchEvent(event);
   //     return true;
  ///  }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            // Handle tap events.
            case KeyEvent.KEYCODE_DPAD_CENTER:
                setContentView(R.layout.activity_main);
              //  startActivity(new Intent(this, MainActivity.class));
                //  count = 5000;
            case KeyEvent.KEYCODE_ENTER:
                //count = 0000;
              //  setContentView(R.layout.activity_main);
                //  toggleStopWatch();
                return true;
            default:
                setContentView(R.layout.activity_main);
             //   startActivity(new Intent(this, MainActivity.class));
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

