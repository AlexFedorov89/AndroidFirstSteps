package com.fedorov.alex.whattheweatheristoday;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public final static String PREV_CITY = "PREV_CITY";

    private static final String TAG = "myLogs";

    //TODO delete next lesson.
    public final static String ON_CREATE = "onCreate()";
    public final static String ON_START = "onStart()";
    public final static String ON_RESUME = "onResume()";
    public final static String ON_RESTART = "onRestart()";
    public final static String ON_PAUSE = "onPause()";
    public final static String ON_STOP = "onStop()";
    public final static String ON_DESTROY = "onDestroy()";

    private final static DataCache dataCache = DataCache.getDataCache();

    TextView temperature;
    ImageView weatherImage;
    TextView cityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityView = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        weatherImage = findViewById(R.id.weatherImage);

        setButtonsOnClickListener();

        setPreviousInstanceState();

        //TODO delete next lesson.
        showToastAndLog(ON_CREATE);

    }

    private void showToastAndLog(String method) {
        Toast.makeText(getApplicationContext(), method, Toast.LENGTH_SHORT).show();
        Log.d(TAG, method);
    }

    private void setPreviousInstanceState() {
        String prevCity = dataCache.getCity();
        String prevTemp = dataCache.getTemp();
        int prevImageRes = dataCache.getImageRes();

        if (prevCity != null) {
            cityView.setText(prevCity);
        }

        if (prevTemp != null) {
            temperature.setText(prevTemp);
        }

        if (prevImageRes != -1) {
            weatherImage.setImageResource(prevImageRes);
        }
    }

    private void setButtonsOnClickListener() {
        findViewById(R.id.sun_small).setOnClickListener(this);
        findViewById(R.id.cloud_small).setOnClickListener(this);
        findViewById(R.id.rain_small).setOnClickListener(this);
        findViewById(R.id.snow_small).setOnClickListener(this);
        findViewById(R.id.btnSettings).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String currentTemp;
        int currentImgRes;

        switch (view.getId()) {
            case R.id.sun_small:
                currentTemp = "+35";
                currentImgRes = R.drawable.sunny;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                Actions.setWeather(temperature, weatherImage, currentTemp, currentImgRes);

                break;
            case R.id.cloud_small:
                currentTemp = "+21";
                currentImgRes = R.drawable.cloud;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                Actions.setWeather(temperature, weatherImage, currentTemp, currentImgRes);

                break;

            case R.id.rain_small:
                currentTemp = "+15";
                currentImgRes = R.drawable.rain;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                Actions.setWeather(temperature, weatherImage, currentTemp, currentImgRes);

                break;

            case R.id.snow_small:
                currentTemp = "-25";
                currentImgRes = R.drawable.snow;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                Actions.setWeather(temperature, weatherImage, currentTemp, currentImgRes);

                break;

            case R.id.btnSettings:
                Intent intent = new Intent(this, CityScreen.class);

                String cityEx = cityView.getText().toString();

                intent.putExtra(PREV_CITY, cityEx);
                // запуск activity
                startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null) {

            String city = data.getStringExtra(CityScreen.NEW_CITY);

            cityView.setText(city);
            dataCache.setCity(city);
        }
    }

    //TODO delete next lesson.
    @Override
    protected void onStart() {
        showToastAndLog(ON_START);

        super.onStart();
    }

    //TODO delete next lesson.
    @Override
    protected void onResume() {
        showToastAndLog(ON_RESUME);

        super.onResume();
    }

    //TODO delete next lesson.
    @Override
    protected void onRestart() {
        showToastAndLog(ON_RESTART);

        super.onRestart();
    }

    //TODO delete next lesson.
    @Override
    protected void onPause() {
        showToastAndLog(ON_PAUSE);

        super.onPause();
    }

    //TODO delete next lesson.
    @Override
    protected void onStop() {
        showToastAndLog(ON_STOP);

        super.onStop();
    }

    //TODO delete next lesson.
    @Override
    protected void onDestroy() {
        showToastAndLog(ON_DESTROY);

        super.onDestroy();
    }
}
