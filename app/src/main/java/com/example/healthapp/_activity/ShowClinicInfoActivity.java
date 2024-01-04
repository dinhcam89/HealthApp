package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthapp.R;

public class ShowClinicInfoActivity extends AppCompatActivity {
    private Button btn_Back;
    private Button btn_Book;
    private Button btn_Book2;
    private Button btn_CallHotline;
    private TextView textView_CallHotline;
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
        btn_CallHotline = findViewById(R.id.btn_CallHotline);
        btn_CallHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0815942559";

                // Tạo Intent với hành động ACTION_CALL
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

                // Đặt số điện thoại cần gọi
                //dialIntent.setData(Uri.parse("tel:" + phoneNumber));
                if(ActivityCompat.checkSelfPermission(v.getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(ShowClinicInfoActivity.this , new String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(dialIntent);
            }
        });
        textView_CallHotline = findViewById(R.id.textView_CallHotline);
        textView_CallHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0815942559";

                // Tạo Intent với hành động ACTION_CALL
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

                // Đặt số điện thoại cần gọi
                //dialIntent.setData(Uri.parse("tel:" + phoneNumber));
                if(ActivityCompat.checkSelfPermission(v.getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(ShowClinicInfoActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(dialIntent);
            }
        });
    }
}