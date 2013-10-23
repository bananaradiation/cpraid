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

    private boolean pauseCount;
    private int count;
    private long startTime, endTime, timeDiff, sumDiff, lcount = 0;
    private float gravityY, gravityZ, accelY, accelZ, rawPitch, compressionPerMin = 0;
    private double calcPitch, vMot = 0;

    private GestureDetector mGestureDetector;

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

        mGestureDetector = new GestureDetector(this, new Controls());

        start = (TextView) findViewById(R.id.start);
        countText = (TextView) findViewById(R.id.countStr);
        rateStr = (TextView) findViewById(R.id.rateStr);
        compressionRate = (TextView) findViewById(R.id.rate);
        countView = (TextView) findViewById(R.id.countVal);
        instruction = (TextView) findViewById(R.id.instruction);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        pauseCount = false;
        count = 0;
        sumDiff = 0;
        startTime = System.currentTimeMillis();
	}

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
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
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

	@Override
	public void onSensorChanged(SensorEvent event) {
        boolean pitchAdj;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelY = event.values[1];
            accelZ = event.values[2];
        }
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravityY = event.values[1];
            gravityZ = event.values[2];
        }
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            rawPitch = event.values[1];
        }

        if (rawPitch > 90){
            calcPitch = ((rawPitch*(-1)) - 90);
            pitchAdj = true;
        }else{
            calcPitch = (rawPitch*(-1));
            pitchAdj = false;
        }

        //adjusts vertical motion vector calculations for user head angle
        if(!pitchAdj){
            vMot = Math.cos(calcPitch)*(accelZ-gravityZ) + Math.cos(calcPitch)*(accelY-gravityY);
        }else{
            vMot = Math.cos(calcPitch)*(accelY-gravityY) + Math.cos(calcPitch)*(accelZ-gravityZ);
        }
    //increases compression count by 1 whenever accel_Y passes 0 threshold
        if(vMot > 5 && !pauseCount) {
            count++;
            pauseCount = true;
            endTime = System.currentTimeMillis();
            timeDiff = endTime - startTime;
            startTime = endTime;
            sumDiff += timeDiff;
            lcount = (long)count;
            compressionPerMin = (((float)lcount / (float)sumDiff) * 1000 * 60);
        }
        if (count >=2) {start.setVisibility(TextView.INVISIBLE);}
        if(vMot < -5 && System.currentTimeMillis()-endTime > 150){pauseCount = false;}
        //text.setText("Pitch: " + calcPitch + "\nvMot: " + vMot + "\nCount: " + count + "\nTime Diff: " + timeDiff + "\nAverage: " + average );
        //text.setText("Accel Y: " + accel_Y + "\nCount: " + count + "\nTime Diff: " + timeDiff + "\nAverage: " + average );
         countView.setText(Integer.toString(count));
         if (compressionPerMin < 95 || compressionPerMin > 105) {
             compressionRate.setTextColor(Color.RED);}
         else
             compressionRate.setTextColor(Color.GREEN);
         compressionRate.setText(String.valueOf(compressionPerMin));
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}
