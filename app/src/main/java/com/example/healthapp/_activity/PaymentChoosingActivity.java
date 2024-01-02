package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.R;
import com.example.healthapp._class.Appointment;
import com.example.healthapp._class.DoctorAppointment;
import com.example.healthapp._fragment.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PaymentChoosingActivity extends AppCompatActivity {
    private TextView textView_DoctorName;
    private TextView textView_Speciality;
    private TextView textView_BookingStatus;
    private ImageButton btn_Back;
    private Button btn_CompleteBooking;
    private FirebaseAuth mAuth;
    private RadioGroup radioGr_PaymentMethod;
    String doctorID, doctorName, doctorSpeciality, chosenDate, chosenHour, userUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_choosing);
        textView_DoctorName = findViewById(R.id.textView_DoctorName);
        textView_Speciality = findViewById(R.id.textView_DoctorSpeciality);
        textView_BookingStatus = findViewById(R.id.textView_BookingStatus);
        btn_CompleteBooking = findViewById(R.id.btn_CompleteBooking);
        btn_Back = findViewById(R.id.btn_Back);
        radioGr_PaymentMethod = findViewById(R.id.radioGr_PaymentMethod);
        mAuth = FirebaseAuth.getInstance();

        // Get User UID
        mAuth.addAuthStateListener(auth -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                userUID = user.getUid();
            } else {
                // Đăng xuất, xử lý theo ý muốn
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDoctorInfo();

        btn_CompleteBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBooking();
                addDoctorSchedule();
                Toast.makeText(PaymentChoosingActivity.this, "Đặt lịch thành công!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDoctorInfo()
    {
        Intent intent = getIntent();
        if (intent != null) {
            doctorID = intent.getStringExtra("doctorID");
            doctorName = intent.getStringExtra("doctorName");
            doctorSpeciality = intent.getStringExtra("doctorSpeciality");
            chosenDate = intent.getStringExtra("chosenDate");
            chosenHour = intent.getStringExtra("chosenHour");


            // Hiển thị dữ liệu trong TextView
            String bookInfo = "Doctor Name: " + doctorName + "\nSpeciality: " + doctorSpeciality;
            textView_BookingStatus.setText("Bạn đã đặt lịch khám vào lúc " + chosenHour + " ngày " + chosenDate);
            textView_DoctorName.setText(doctorName);
            textView_Speciality.setText(doctorSpeciality);
        }
    }
    public void addBooking()
    {
        boolean isPay;
        int radId = radioGr_PaymentMethod.getCheckedRadioButtonId();
        if (radId == R.id.radioButton_PayWithCash) {
            //tạo instance là FullTime
            isPay = false;
        } else {
            //Tạo instance là Partime
            isPay = true;
        }

        Appointment newAppointment = new Appointment(doctorID, userUID, chosenDate, chosenHour, isPay);

        CollectionReference BookingRef = FirebaseFirestore.getInstance().collection("Booking");

        BookingRef.add(newAppointment.toMap())
                .addOnSuccessListener(documentReference -> {
                    // Ghi dữ liệu thành công
                    String autoID = documentReference.getId();

                    // TODO: Thực hiện các hành động khác sau khi thêm thành công

                })
                .addOnFailureListener(e -> {
                    // Xử lý khi ghi dữ liệu thất bại
                    e.printStackTrace(); // In ra lỗi
                    Toast.makeText(PaymentChoosingActivity.this, "Đã xảy ra lỗi. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                });
    }

    public void addDoctorSchedule()
    {

        DoctorAppointment doctorAppointment = new DoctorAppointment(doctorID, chosenDate, chosenHour, true);
        CollectionReference DoctorScheduleRef = FirebaseFirestore.getInstance().collection("DoctorSchedule");

        DoctorScheduleRef.add(doctorAppointment.toMap())
                .addOnSuccessListener(documentReference -> {
                    // Ghi dữ liệu thành công
                    String autoID = documentReference.getId();
                })
                .addOnFailureListener(e -> {
                    // Xử lý khi ghi dữ liệu thất bại
                    e.printStackTrace(); // In ra lỗi
                    Toast.makeText(PaymentChoosingActivity.this, "Đã xảy ra lỗi. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                });
    }
}