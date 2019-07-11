package com.fedorov.alex.whattheweatheristoday;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Actions {

    private final static String TAG = "Actions";

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
}
