package com.fedorov.alex.whattheweatheristoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public final static String PREV_CITY = "PREV_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем сообщение из объекта intent
        setNewCity();

        findViewById(R.id.sun_small).setOnClickListener(this);
        findViewById(R.id.cloud_small).setOnClickListener(this);
        findViewById(R.id.rain_small).setOnClickListener(this);
        findViewById(R.id.snow_small).setOnClickListener(this);
        findViewById(R.id.btnSettings).setOnClickListener(this);

    }

    private void setNewCity() {
        Intent intent = getIntent();

        String city = intent.getStringExtra(CityScreen.NEW_CITY);

        if (city != null) {
            TextView cityView = findViewById(R.id.city);
            cityView.setText(city);
        }
    }

    @Override
    public void onClick(View view) {
        String testTemp;
        int testImgRes;

        TextView temperature = findViewById(R.id.temperature);
        ImageView weatherImage = findViewById(R.id.weatherImage);

        switch (view.getId()) {
            case R.id.sun_small:
                testTemp = "+35";
                testImgRes = R.drawable.sunny;

                Actions.setWeather(temperature, weatherImage, testTemp, testImgRes);

                break;
            case R.id.cloud_small:
                testTemp = "+21";
                testImgRes = R.drawable.cloud;

                Actions.setWeather(temperature, weatherImage, testTemp, testImgRes);

                break;

            case R.id.rain_small:
                testTemp = "+15";
                testImgRes = R.drawable.rain;

                Actions.setWeather(temperature, weatherImage, testTemp, testImgRes);

                break;

            case R.id.snow_small:
                testTemp = "-25";
                testImgRes = R.drawable.snow;

                Actions.setWeather(temperature, weatherImage, testTemp, testImgRes);

                break;

            case R.id.btnSettings:
                Intent intent = new Intent(this, CityScreen.class);

                TextView city = findViewById(R.id.city);

                String cityEx = city.getText().toString();

                intent.putExtra(PREV_CITY, cityEx);
                // запуск activity
                startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
