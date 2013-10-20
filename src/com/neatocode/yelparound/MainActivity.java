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

public class MainActivity extends Activity implements SensorEventListener {
	
	private static final String LOG_TAG = "SensorTest";
	private TextView text;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
    private boolean flag;
    private int count;
    private long startTime, endTime, timeDiff, sumDiff, average;

	
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

		text = (TextView) findViewById(R.id.text);

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
            average = sumDiff / (long)count;
        }
        if(accel_Y < -5){flag = false;}
        text.setText("Accel Y: " + accel_Y + "\nCount: " + count + "\nTime Diff: " + timeDiff + "\nAverage: " + average );
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
