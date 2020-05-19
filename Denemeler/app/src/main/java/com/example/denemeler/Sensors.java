package com.example.denemeler;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

public class Sensors extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mLight;

    private TextView sensorListText,value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight=sensorManager.getDefaultSensor((Sensor.TYPE_LIGHT));


        List<Sensor> sensorsList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorListText = (TextView) findViewById(R.id.textView_sensors);
        value=(TextView)findViewById(R.id.value);

        StringBuffer allSensors = new StringBuffer();
        int i=1;
        for (Sensor sens : sensorsList)
        {
            allSensors.append(i+"."+sens.getName() + "\n");
            i++;
        }
        sensorListText.setText(allSensors);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            float lux = sensorEvent.values[0];
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            if (lux < 10) {
                sensorListText.setBackgroundColor(Color.BLACK);
                sensorListText.setTextColor(Color.WHITE);
                value.setText("Light value of environment="+lux);
            } else {
                sensorListText.setBackgroundColor(Color.WHITE);
                sensorListText.setTextColor(Color.BLACK);
                value.setText("Light value of environment="+lux);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,mLight,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
