package com.neatocode.yelparound;

/**
 * Created by NarineC on 10/22/13.
 * used from http://b2cloud.com.au/how-to-guides/capture-glass-d-pad-events-for-android
 *
 */
    import android.app.Activity;
    import android.app.Activity;
    import android.os.Bundle;
    import android.os.SystemClock;
    import android.util.Log;
    import android.view.KeyEvent;
    import android.widget.Chronometer;
    import android.widget.TextView;
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

    /**
     * Created by NarineC on 10/22/13.
     */

public class controlls extends GestureDetector.SimpleOnGestureListener
    {

        private static final int SWIPE_MIN_DISTANCE = 100;
        private static final int SWIPE_THRESHOLD_VELOCITY = 1000;

        @Override
        public boolean onScroll(MotionEvent start, MotionEvent finish, float distanceX, float distanceY) {
            if(finish.getX() > start.getX()) {
                Log.d("Event", "On Scroll Forward");
            }
            else {
                Log.d("Event", "On Scroll Backward");
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX, float velocityY) {
            try {
                float totalXTraveled = finish.getX() - start.getX();
                float totalYTraveled = finish.getY() - start.getY();
                if (Math.abs(totalXTraveled) > Math.abs(totalYTraveled)) {
                    if (Math.abs(totalXTraveled) > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        if (totalXTraveled > 10) {
                            Log.d("Event", "On Fling Forward");
                        }
                        else {
                            Log.d("Event", "On Fling Backward");
                        }
                    }
                } else {
                    if (Math.abs(totalYTraveled) > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                        if(totalYTraveled > 0) {
                            Log.d("Event", "On Fling Down");
                        } else {
                            Log.d("Event", "On Fling Up");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("Event", "On Double Tap");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("Event", "On Single Tap");
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }



