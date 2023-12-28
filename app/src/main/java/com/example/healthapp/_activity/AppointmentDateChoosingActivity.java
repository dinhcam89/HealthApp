package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healthapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AppointmentDateChoosingActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private View bgView;
    private View rectangle62;
    private FrameLayout frameLayout;
    private ImageButton btn_Back;
    private TextView titleTextView;
    private ImageView doctorAvatarImageView;
    private TextView doctorNameTextView;
    private TextView doctorSpecialityTextView;
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
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        getDoctorInfor();



        btn8h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           btn8h.setSelected(!btn8h.isSelected());
            }
        });

        btn9h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn9h.setSelected(!btn9h.isSelected());
            }
        });

        btn10h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn10h.setSelected(!btn10h.isSelected());
            }
        });

        btn13h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn13h.setSelected(!btn13h.isSelected());
            }
        });

        btn14h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn14h.setSelected(!btn14h.isSelected());
            }
        });

        btn15h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn15h.setSelected(!btn15h.isSelected());
            }
        });

        btn8h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(btn8h);
            }
        });

        btn9h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(btn9h);
            }
        });

        btn10h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(btn10h);
            }
        });

        btn13h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(btn13h);
            }
        });

        btn14h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(btn14h);
            }
        });

        btn15h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(btn15h);
            }
        });
    }

    private Button selectedButton = null;

    private void selectButton(Button button) {
        if (selectedButton == button) {
            // Nút đã được chọn trước đó, hủy chọn
            selectedButton.setSelected(false);
            selectedButton.setEnabled(true);
            selectedButton = null;
        } else {
            // Chọn một nút mới
            if (selectedButton != null) {
                selectedButton.setSelected(false);
                selectedButton.setEnabled(true);
            }

            button.setSelected(true);
            button.setEnabled(false);
            selectedButton = button;
        }
    }
    private void initUI() {
        constraintLayout = findViewById(R.id.button);
        bgView = findViewById(R.id._bg__tracuudonthuoc2_ek2);
        rectangle62 = findViewById(R.id.rectangle_62);
        frameLayout = findViewById(R.id.frameLayout);
        btn_Back = findViewById(R.id.btn_Back);
        titleTextView = findViewById(R.id.tittle_tracuudonthuoc);
        doctorAvatarImageView = findViewById(R.id.imgView_DoctorAvatar);
        doctorNameTextView = findViewById(R.id.textView_DoctorName);
        doctorSpecialityTextView = findViewById(R.id.textView_DoctorSpeciality);
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
    private void getDoctorInfor()
    {
        Intent intent = getIntent();
        if (intent != null) {
            String doctorName = intent.getStringExtra("doctorName");
            String doctorSpeciality = intent.getStringExtra("doctorSpeciality");

            // Hiển thị dữ liệu trong TextView
            String bookInfo = "Doctor Name: " + doctorName + "\nSpeciality: " + doctorSpeciality;
            doctorNameTextView.setText(doctorName);
            doctorSpecialityTextView.setText(doctorSpeciality);
        }
    }
}