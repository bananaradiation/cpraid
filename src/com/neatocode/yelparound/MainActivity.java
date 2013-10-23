package com.neatocode.yelparound;

import java.util.List;

import android.app.Activity;
import android.content.Context;
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

public class MainActivity extends Activity implements SensorEventListener {

	private static final String LOG_TAG = "SensorTest";
	private TextView start, countView, compressionRate, instruction, countText, rateStr;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
    private boolean flag;
    private int count;
    private long startTime, endTime, timeDiff, sumDiff, compressionPerMin;
 //   private GestureDetector gestureDetector;

	private static void removeBackgrounds(final View aView) {
		aView.setBackgroundDrawable(null);
		aView.setBackgroundColor(Color.TRANSPARENT);
		aView.setBackgroundResource(0);
		if (aView instanceof ViewGroup) {
			final ViewGroup group = (ViewGroup) aView;
			final int childCount = group.getChildCount();
			for(int i = 0; i < childCount; i++) {
				final View child = group.getChildAt(i);
				removeBackgrounds(child);
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		//removeBackgrounds(getWindow().getDecorView());

        start = (TextView) findViewById(R.id.start);
        countText = (TextView) findViewById(R.id.countStr);
        rateStr = (TextView) findViewById(R.id.rateStr);
        compressionRate = (TextView) findViewById(R.id.rate);
        countView = (TextView) findViewById(R.id.countVal);
        instruction = (TextView) findViewById(R.id.instruction);

      	mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        flag = false;
        count = 0; sumDiff = 0;
        startTime = System.currentTimeMillis();
	}
    @Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do something here if sensor accuracy changes.
		// You must implement this callback in your code.
	}

	@Override
	protected void onResume() {
		super.onResume();
        //order doesn't matter here, move on
//        mSensorManager.registerListener(this, mGravity,
//                SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
        float accel_Y = 0;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accel_Y = event.values[1] - 15;
        }
    //increases compression count by 1 whenever accel_Y passes 0 threshold
        if(accel_Y > 0 && flag == false) {
            count++;
            flag = true;
            endTime = System.currentTimeMillis();
            timeDiff = endTime - startTime;
            startTime = endTime;
            sumDiff += timeDiff;
            compressionPerMin = (count / (long)sumDiff) * 1000 * 60;

        }
        if(accel_Y < -5){flag = false;}
        //text.setText("Accel Y: " + accel_Y + "\nCount: " + count + "\nTime Diff: " + timeDiff + "\nAverage: " + average );
            //countView.setText(count);
        //TextView rateView = (TextView)findViewById(R.id.editText);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}
