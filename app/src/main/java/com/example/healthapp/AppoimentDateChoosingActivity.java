package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;

public class AppoimentDateChoosingActivity extends AppCompatActivity {
    /*Add in Oncreate() funtion after setContentView()*/
    DatePicker simpleDatePicker;// initiate a date picker
     // get the selected day of the month
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoiment_date_choosing);

        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        int day = simpleDatePicker.getDayOfMonth();
        int month = simpleDatePicker.getMonth(); // get the selected month
        int year = simpleDatePicker.getYear(); // get the selected year


    }
}