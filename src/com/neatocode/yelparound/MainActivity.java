package com.neatocode.yelparound;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends Activity implements SensorEventListener,
		LocationListener {
	
	private static final String LOG_TAG = "SensorTest";
	private TextView text;
	private TextView locationText;
	private SensorManager mSensorManager;
	private Sensor mOrientation;
    private Sensor mAccelerometer;
    private Sensor mGravity;
	private LocationManager mLocationManager;
	
	Float azimuth_angle;
	Float pitch_angle;
	Float roll_angle;
	Double networkLat;
	Double networkLon;
	Double gpsLat;
	Double gpsLon;
    File file;

    float [] accelerometer;
    float [] gravity;

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

		locationText = (TextView) findViewById(R.id.location);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

//        String a = "Found sensor: ";
//		for(Sensor sensor : sensors) {
//            a += sensor.getName() + "\n";
//        }
//        text.setText(a);

//        File sdcard = Environment.getExternalStorageDirectory();
//        file = new File(sdcard,"/my_file.txt");


        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

		//mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
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
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometer = new float[] { event.values[0], event.values[1], event.values[2] };
        }
//        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
//            gravity = new float[] { event.values[0], event.values[1], event.values[2] };
//        }

		// Do something with these orientation angles.
		text.setText("accelerometer: \n" + accelerometer[0] + "gravity: \n"
				+ gravity);

    }


	@Override
	public void onLocationChanged(Location location) {
		locationText.setText(location.getLatitude() + "\n"
				+ location.getLongitude());
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
