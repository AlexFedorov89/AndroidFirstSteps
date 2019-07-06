package com.fedorov.alex.whattheweatheristoday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setSunny(View view) {
        String testTemp = "+35";
        int testImgResSunny = R.drawable.sunny;

        setWeather(testTemp, testImgResSunny);
    }

    public void setCloud(View view) {
        String testTemp = "+21";
        int testImgResSunny = R.drawable.cloud;

        setWeather(testTemp, testImgResSunny);
    }

    public void setRain(View view) {
        String testTemp = "+15";
        int testImgResSunny = R.drawable.rain;

        setWeather(testTemp, testImgResSunny);
    }


    public void setSnow(View view) {
        String testTemp = "-25";
        int testImgResSunny = R.drawable.snow;

        setWeather(testTemp, testImgResSunny);
    }

    public void setWeather(String temp, int imageResource){
        TextView temperature = (TextView) findViewById(R.id.temperature);
        temperature.setText(temp);

        ImageView weatherImage = (ImageView) findViewById(R.id.weatherImage);
        weatherImage.setImageResource(imageResource);
    }
}
