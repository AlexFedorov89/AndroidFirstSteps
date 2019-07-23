package com.fedorov.alex.whattheweatheristoday;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class WeatherScreen extends Fragment {
    private static final String CURRENT_TEMP = "currentTemp";
    private static final String CURRENT_IMG_RES = "currentImgRes";
    private static final String CURRENT_CITY = "currentCity";

    private String mCurrentTemp;
    private String mCurrentCity;
    private int mcurrentImgRes;

    public WeatherScreen() {
        // Required empty public constructor
    }

    public static WeatherScreen newInstance(String currentTemp, String currentCity, int currentImgRes) {
        WeatherScreen fragment = new WeatherScreen();
        Bundle args = new Bundle();
        args.putString(CURRENT_TEMP, currentTemp);
        args.putString(CURRENT_CITY, currentCity);
        args.putInt(CURRENT_IMG_RES, currentImgRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCurrentTemp = getArguments().getString(CURRENT_TEMP);
            mCurrentCity = getArguments().getString(CURRENT_CITY);
            mcurrentImgRes = getArguments().getInt(CURRENT_IMG_RES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_screen, container, false);
    }
}
