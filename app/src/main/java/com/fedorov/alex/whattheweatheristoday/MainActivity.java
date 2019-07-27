package com.fedorov.alex.whattheweatheristoday;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Intent.ACTION_VIEW;


public class MainActivity extends AppCompatActivity implements Observer {

    private Fragment mCurrentFragment;

    private static final String TAG = "myLogs";

    private final static DataCache dataCache = DataCache.getDataCache();

    private TextView temperature;
    private ImageView weatherImage;
    private TextView cityView;

    private String currentCity;
    private String currentTemp;
    private int currentImgRes;

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

        findViewById(R.id.btnSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCurrentFragIsSettings()) {
                    changeFragment();
                }
            }
        });

        findViewById(R.id.sun_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTemp = "+35";
                currentImgRes = R.drawable.sunny;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                setWeather(temperature, weatherImage, currentTemp, currentImgRes);
            }
        });

        findViewById(R.id.cloud_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTemp = "+21";
                currentImgRes = R.drawable.cloud;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                setWeather(temperature, weatherImage, currentTemp, currentImgRes);
            }
        });

        findViewById(R.id.rain_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTemp = "+15";
                currentImgRes = R.drawable.rain;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                setWeather(temperature, weatherImage, currentTemp, currentImgRes);

            }
        });

        findViewById(R.id.snow_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTemp = "-25";
                currentImgRes = R.drawable.snow;
                dataCache.setTemp(currentTemp);
                dataCache.setImageRes(currentImgRes);

                setWeather(temperature, weatherImage, currentTemp, currentImgRes);

            }
        });

        findViewById(R.id.city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    currentCity = cityView.getText().toString();
                    Intent intent = new Intent(ACTION_VIEW, Uri.parse("https://ru.wikipedia.org/wiki/" + currentCity));
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }

    private void changeFragment() {

        mCurrentFragment = Settings.newInstance(cityView.getText().toString());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment, mCurrentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private boolean isCurrentFragIsSettings() {
        return mCurrentFragment instanceof Settings;
    }


    @Override
    public void onBackPressed() {

        if (isCurrentFragIsSettings()) {

            TextView cityInput = findViewById(R.id.cityInput);
            cityView.setText(cityInput.getText().toString());

            mCurrentFragment = null;
        }

        super.onBackPressed();
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
    public void updateCity(String city) {
        cityView.setText(city);
    }
}
