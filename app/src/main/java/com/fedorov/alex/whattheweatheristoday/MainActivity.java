package com.fedorov.alex.whattheweatheristoday;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Intent.ACTION_VIEW;


public class MainActivity extends AppCompatActivity {


    public final static String CITY = "CITY";

    private static final String TAG = "myLogs";

    private final static DataCache dataCache = DataCache.getDataCache();
    public static final int REQUEST_CODE = 1;

    TextView temperature;
    ImageView weatherImage;
    TextView cityView;

    String currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityView = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        weatherImage = findViewById(R.id.weatherImage);

        setButtonsOnClickListener();

        setPreviousInstanceState();
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
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushTheButton(view);
            }
        };

        findViewById(R.id.sun_small).setOnClickListener(onClickListener);
        findViewById(R.id.cloud_small).setOnClickListener(onClickListener);
        findViewById(R.id.rain_small).setOnClickListener(onClickListener);
        findViewById(R.id.snow_small).setOnClickListener(onClickListener);
        findViewById(R.id.btnSettings).setOnClickListener(onClickListener);
        findViewById(R.id.city).setOnClickListener(onClickListener);
    }


    public void pushTheButton(View view) {
        String currentTemp;
        int currentImgRes;

        switch (view.getId()) {
            case R.id.city:
                try {
                    currentCity = cityView.getText().toString();
                    Intent intent = new Intent(ACTION_VIEW, Uri.parse("https://ru.wikipedia.org/wiki/" + currentCity));
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }

                break;
            case R.id.sun_small:
                currentTemp = "+35";
                currentImgRes = R.drawable.sunny;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                setWeather(temperature, weatherImage, currentTemp, currentImgRes);

                break;
            case R.id.cloud_small:
                currentTemp = "+21";
                currentImgRes = R.drawable.cloud;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                setWeather(temperature, weatherImage, currentTemp, currentImgRes);

                break;

            case R.id.rain_small:
                currentTemp = "+15";
                currentImgRes = R.drawable.rain;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                setWeather(temperature, weatherImage, currentTemp, currentImgRes);

                break;

            case R.id.snow_small:
                currentTemp = "-25";
                currentImgRes = R.drawable.snow;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                setWeather(temperature, weatherImage, currentTemp, currentImgRes);

                break;

            case R.id.btnSettings:
                Intent cityScreenIntent = new Intent(this, CityScreen.class);

                currentCity = cityView.getText().toString();

                cityScreenIntent.putExtra(CITY, currentCity);
                // запуск activity
                startActivityForResult(cityScreenIntent, REQUEST_CODE);
        }
    }

    public static void setWeather(TextView temperature, ImageView weatherImage, String temp, int imageResource) {
        if (temp != null)
            temperature.setText(temp);
        else {
            Log.d(TAG, "Error. Not found TextView with id = 'temperature'");
        }

        if (weatherImage != null) {
            weatherImage.setImageResource(imageResource);
        } else {
            Log.d(TAG, "Error. Not found ImageView with id = 'weatherImage'");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);

            return;
        }

        if (resultCode == RESULT_OK && data != null) {
            try {
                String city = data.getStringExtra(CITY);

                cityView.setText(city);
                dataCache.setCity(city);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

}
