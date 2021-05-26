package br.edu.ceunsp.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class ActivitySensorLuz extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensorLuz;
    private Sensor sensorProx;
    private TextView textViewLuz;
    private TextView textViewProx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_luz);

        textViewLuz = findViewById(R.id.textViewLuz);
        textViewProx = findViewById(R.id.textViewProx);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorProx = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventLuz = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float vl = event.values[0];
                textViewLuz.setText(String.valueOf(vl));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventProx = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                float vl = event.values[0];
                textViewProx.setText(String.valueOf(vl));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(sensorEventLuz, sensorLuz,
                SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(sensorEventProx, sensorProx,
                SensorManager.SENSOR_DELAY_UI);
    }
}