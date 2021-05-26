package br.edu.ceunsp.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    TextView textView;
    TextView textViewX;
    TextView textViewY;
    TextView textViewZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textViewX = findViewById(R.id.textViewX);
        textViewY = findViewById(R.id.textViewY);
        textViewZ = findViewById(R.id.textViewZ);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ALL);
        Iterator<Sensor> iterator = lista.iterator();
        String sensores = "";
        while (iterator.hasNext()) {
            Sensor sensor = iterator.next();
            sensores += " - " + sensor.getName() + " " + sensor.getType()+ "\n";
        }
        sensor.getType();
        textView.setText(sensores);

    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        textViewX.setText( String.valueOf( x ) );
        textViewY.setText( String.valueOf( y ) );
        textViewZ.setText( String.valueOf( z ) );

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onClickLuz(View view) {

        Intent intent = new Intent(MainActivity.this, ActivitySensorLuz.class);
        startActivity(intent);
    }
}