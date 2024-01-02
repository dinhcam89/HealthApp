package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthapp.R;

public class ShowClinicInfoActivity extends AppCompatActivity {
    private Button btn_Back;
    private Button btn_Book;
    private Button btn_Book2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clinic_info);

        btn_Back = findViewById(R.id.btn_Back);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_Book = findViewById(R.id.btn_Book);
        btn_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorChoosingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_Book2 = findViewById(R.id.btn_Book2);
        btn_Book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorChoosingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}