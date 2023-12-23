package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthapp.R;
import com.example.healthapp._activity.DoctorChoosingActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainMenuActivity extends AppCompatActivity {

    /*Button btnSignOut;
    FirebaseAuth emailAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnSignOut = findViewById(R.id.button);
        emailAuth = FirebaseAuth.getInstance();
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }*/
    private CardView Bacsi;
    private CardView Tracuu;

    private Button datlich1;

    private Button datlich2;
    private Intent intent_bacsi;
    private Intent intent_tracuu;
    private Intent intent_danhmuc;
    private Intent intent_datlich1;
    private Intent intent_datlich2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Bacsi = findViewById(R.id.bacsi);
        intent_bacsi = new Intent(this, DoctorChoosingActivity.class);
      //  Tracuu = findViewById(R.id.tracuu);

        datlich1 = findViewById(R.id.btn_book1);
        intent_datlich1 = new Intent(this, AppointmentDateChoosingActivity.class);
        datlich2 = findViewById(R.id.btn_book2);

        //intent_tracuu = new Intent(this, TraCuuActivity.class);
        //intent_danhmuc = new Intent(this, DanhMucActivity.class);
        Bacsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_bacsi);
            }
        });

        datlich1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_datlich1);
            }
        });
        datlich2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_datlich1);
            }
        });
    }
}