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

	private Sensor mAccelerometer, mGravity;
	
	Float azimuth_angle;

	Float pitch_angle;
	
	Float roll_angle;
	
	Double networkLat;
	
	Double networkLon;
	
	Double gpsLat;
	
	Double gpsLon;
	
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
		List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		for(Sensor sensor : sensors) {
			Log.i(LOG_TAG, "Found sensor: " + sensor.getName());
		}
		
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do something here if sensor accuracy changes.
		// You must implement this callback in your code.
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGravity,
                SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

        float accelerometer1 = 0;
        float accelerometer2 = 0;
        float accelerometer3 = 0;
        float gravity1 = 0;
        float gravity2 = 0;
        float gravity3 = 0;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometer1 = event.values[0];
            accelerometer2 = event.values[1];
            accelerometer3 = event.values[2];
        }else if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravity1 = event.values[0];
            gravity2 = event.values[1];
            gravity3 = event.values[2];
        }

        text.setText("Accel X: " + accelerometer1 +"\nAccel Y: " + accelerometer2 +"\nAccel Z: " + accelerometer3 + "\nGrav X: " + gravity1 +"\nGrav Y: " + gravity2 +"\nGrav Z: " + gravity3);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
