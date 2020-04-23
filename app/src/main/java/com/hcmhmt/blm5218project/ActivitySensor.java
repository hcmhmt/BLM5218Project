package com.hcmhmt.blm5218project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivitySensor extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager ;
    TextView lightSensor;
    List<Sensor> listsensor;
    List<String> liststring ;
    ArrayAdapter<String> adapter ;

    SensorManager sensorManager2;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);


        lightSensor = findViewById(R.id.lightsensor);

        sensorManager2 = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager2.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager2.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager2.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            if(event.values[0] > 10.0){
                lightSensor.setBackgroundColor(Color.WHITE);
                lightSensor.setTextColor(Color.BLACK);
            }
            else{
                lightSensor.setBackgroundColor(Color.BLACK);
                lightSensor.setTextColor(Color.WHITE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
