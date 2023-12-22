package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AppointmentDateChoosingActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private View bgView;
    private View rectangle62;
    private FrameLayout frameLayout;
    private ImageView arrowBack;
    private TextView titleTextView;
    private ImageView doctorAvatarImageView;
    private TextView doctorNameTextView;
    private TextView khoaKhamTextView;
    private TextView chooseDateTextView;
    private DatePicker chooseAppointmentDatePicker;
    private LinearLayout linearLayout1;
    private Button btn8h;
    private Button btn9h;
    private Button btn10h;
    private LinearLayout linearLayout2;
    private Button btn13h;
    private Button btn14h;
    private Button btn15h;
    private TextView chosenDateTextView;
    private ImageButton bookButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_date_choosing);

        initUI();

        chosenDateTextView = findViewById(R.id.textView_ChoosenDate);
        chooseAppointmentDatePicker = findViewById(R.id.datePicker_ChooseAppointmentDate);

        // Lấy ngày mặc định từ DatePicker và hiển thị trên TextView
        updateChosenDate();
        Calendar currentDate = Calendar.getInstance();

        // Thiết lập ngày tối thiểu cho DatePicker là ngày hiện tại
        chooseAppointmentDatePicker.setMinDate(currentDate.getTimeInMillis());
        // Thiết lập sự kiện lắng nghe cho DatePicker
        chooseAppointmentDatePicker.init(
                chooseAppointmentDatePicker.getYear(),
                chooseAppointmentDatePicker.getMonth(),
                chooseAppointmentDatePicker.getDayOfMonth(),
                (view, year, monthOfYear, dayOfMonth) -> updateChosenDate()
        );

    }
    private void initUI() {
        constraintLayout = findViewById(R.id.button);
        bgView = findViewById(R.id._bg__tracuudonthuoc2_ek2);
        rectangle62 = findViewById(R.id.rectangle_62);
        frameLayout = findViewById(R.id.frameLayout);
        arrowBack = findViewById(R.id.arrow_back);
        titleTextView = findViewById(R.id.tittle_tracuudonthuoc);
        doctorAvatarImageView = findViewById(R.id.imgView_DoctorAvatar);
        doctorNameTextView = findViewById(R.id.textView_DoctorName);
        khoaKhamTextView = findViewById(R.id.textView_Khoakham);
        chooseDateTextView = findViewById(R.id.textView_ChooseDate);
        chooseAppointmentDatePicker = findViewById(R.id.datePicker_ChooseAppointmentDate);
        linearLayout1 = findViewById(R.id.linear1);
        btn8h = findViewById(R.id.btn_8h);
        btn9h = findViewById(R.id.btn_9h);
        btn10h = findViewById(R.id.btn_10h);
        linearLayout2 = findViewById(R.id.linear2);
        btn13h = findViewById(R.id.btn_13h);
        btn14h = findViewById(R.id.btn_14h);
        btn15h = findViewById(R.id.btn_15h);
        chosenDateTextView = findViewById(R.id.textView_ChoosenDate);
        bookButton = findViewById(R.id.btn_Book);
    }
    private void updateChosenDate() {
        int day = chooseAppointmentDatePicker.getDayOfMonth();
        int month = chooseAppointmentDatePicker.getMonth();
        int year = chooseAppointmentDatePicker.getYear();

        // Tạo một đối tượng Calendar để định dạng ngày
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        // Định dạng ngày tháng năm và hiển thị trên TextView
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = sdf.format(calendar.getTime());
        chosenDateTextView.setText("Ngày đã chọn: " + formattedDate);
    }
}