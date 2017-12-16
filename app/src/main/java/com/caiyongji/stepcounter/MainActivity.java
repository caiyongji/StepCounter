package com.caiyongji.stepcounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.hardware.Sensor.STRING_TYPE_STEP_DETECTOR;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "caiyongji";
    private View background;
    private Button button;
    private ImageView star1;
    private ImageView star2;
    private ImageView star3;
    private ImageView star4;
    private ImageView star5;
    private boolean isStarted = false;
    private int stepCounter = 0;
    private SensorManager sensorManager;
    private Sensor sensor;
    SensorEventListener stepDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStarted) {
                    isStarted = false;
                    background.setBackground(getDrawable(R.color.endRed));
                    button.setBackground(getDrawable(R.color.endRed));
                    button.setText(getText(R.string.start));
                    stepCounter = 0;
                    sensorManager.unregisterListener(stepDetector);
                    turnOffAll();
                } else {
                    isStarted = true;
                    background.setBackground(getDrawable(R.color.startGreen));
                    button.setBackground(getDrawable(R.color.startGreen));
                    button.setText(String.valueOf(0));
                    sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_FASTEST);
                }
            }
        });

        //set sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                stepCounter += sensorEvent.values[0] * 1000;
                button.setText(String.valueOf(stepCounter));
                switch (stepCounter){
                    case 1000:
                        turnHalf(star1);
                        break;
                    case 2000:
                        turnFull(star1);
                        break;
                    case 3000:
                        turnHalf(star2);
                        break;
                    case 4000:
                        turnFull(star2);
                        break;
                    case 5000:
                        turnHalf(star3);
                        break;
                    case 6000:
                        turnFull(star3);
                        break;
                    case 7000:
                        turnHalf(star4);
                        break;
                    case 8000:
                        turnFull(star4);
                        break;
                    case 9000:
                        turnHalf(star5);
                        break;
                    case 10000:
                        turnFull(star5);
                        break;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    private void initView() {
        background = findViewById(R.id.StepBackground);
        button = findViewById(R.id.StepBtn);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);
        star5 = findViewById(R.id.star5);
    }

    private void turnHalf(View v){
        v.setBackground(getDrawable(R.drawable.ic_star_half_white_48px));
    }

    private void turnFull(View v){
        v.setBackground(getDrawable(R.drawable.ic_star_white_48px));
    }

    private  void turnOffAll(){
        star1.setBackground(getDrawable(R.drawable.ic_star_border_white_48px));
        star2.setBackground(getDrawable(R.drawable.ic_star_border_white_48px));
        star3.setBackground(getDrawable(R.drawable.ic_star_border_white_48px));
        star4.setBackground(getDrawable(R.drawable.ic_star_border_white_48px));
        star5.setBackground(getDrawable(R.drawable.ic_star_border_white_48px));
    }
}
