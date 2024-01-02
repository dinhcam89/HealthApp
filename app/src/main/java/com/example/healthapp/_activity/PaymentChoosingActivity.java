package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthapp.R;

public class PaymentChoosingActivity extends AppCompatActivity {
    private TextView textView_DoctorName;
    private TextView textView_Speciality;
    private Button btn_Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_choosing);
        textView_DoctorName = findViewById(R.id.textView_DoctorName);
        textView_Speciality = findViewById(R.id.textView_DoctorSpeciality);
        btn_Back = findViewById(R.id.btn_Back);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDoctorInfo();
    }

    private void getDoctorInfo()
    {
        Intent intent = getIntent();
        if (intent != null) {
            String doctorName = intent.getStringExtra("doctorName");
            String doctorSpeciality = intent.getStringExtra("doctorSpeciality");

            // Hiển thị dữ liệu trong TextView
            String bookInfo = "Doctor Name: " + doctorName + "\nSpeciality: " + doctorSpeciality;
            textView_DoctorName.setText(doctorName);
            textView_Speciality.setText(doctorSpeciality);
        }
    }
}