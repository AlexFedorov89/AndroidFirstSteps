package com.fedorov.alex.whattheweatheristoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CityScreen extends AppCompatActivity {
    private String city;
    public final static String NEW_CITY = "NEW_CITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_screen);

        // Получаем сообщение из объекта intent
        Intent intent = getIntent();
        city = intent.getStringExtra(MainActivity.PREV_CITY);

        EditText cityView = findViewById(R.id.cityInput);
        cityView.setText(city);
    }


    @Override
    public void onBackPressed() {

        TextView cityView = findViewById(R.id.cityInput);

        city = cityView.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(NEW_CITY, city);
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }
}
