package com.hcmhmt.blm5218project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMotionlessPhone extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float[] mGravity;
    private float mA;
    private float mACurrent;
    private float mALast;
    TextView tw;
    CountDownTimer counter;
    private boolean isStart,isChecked,isBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motionless_phone);
        isChecked=false;
        isStart = false;
        isBackPressed=false;
        mA = 0.00f;

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mACurrent = SensorManager.GRAVITY_EARTH;
        mALast = SensorManager.GRAVITY_EARTH;

        tw = (TextView)findViewById(R.id.tw_motionless);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && !isChecked){
            mGravity = event.values.clone();

            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];

            mALast = mACurrent;
            mACurrent = (float)Math.sqrt(x*x + y*y + z*z);
            float delta = mACurrent - mALast;
            mA = mA * 0.9f + delta;

            if(mA > 1){
                isStart = false;
                tw.setText("HAREKETLİYİZ! SIFIRLANDI");
            }
            else{
                if(!isStart){
                    counter(5000);
                }
            }
        }

    }

    private void counter(long min) {
        isStart = true;
        if(counter != null) {
            counter.cancel();
            counter = null;
        }
        counter = new CountDownTimer(min, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                tw.setText(String.format("%d:%d:%d", hours, minutes, seconds));
            }
            public void onFinish() {
                isStart = false;
                isChecked = true;
                if(!isBackPressed){
                    Toast.makeText(getApplicationContext(), "Zaman Doldu | Uygulama Kapanıyor..!", Toast.LENGTH_LONG).show();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
                }
            }
        };
        counter.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBackPressed=true;
        finish();
    }
}
