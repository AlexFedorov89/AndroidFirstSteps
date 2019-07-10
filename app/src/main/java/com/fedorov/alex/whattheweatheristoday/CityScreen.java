package com.fedorov.alex.whattheweatheristoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CityScreen extends AppCompatActivity {

    public final static String NEW_CITY = "NEW_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_screen);

        // Получаем сообщение из объекта intent
        Intent intent = getIntent();
        String city = intent.getStringExtra(MainActivity.PREV_CITY);

        EditText cityView = findViewById(R.id.cityInput);
        cityView.setText(city);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);

        TextView city = findViewById(R.id.cityInput);

        String cityNew = city.getText().toString();

        intent.putExtra(NEW_CITY, cityNew);
        // запуск activity
        startActivity(intent);
    }
}
