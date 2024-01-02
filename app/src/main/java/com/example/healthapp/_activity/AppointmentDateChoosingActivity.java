package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.R;
import com.example.healthapp._class.Appointment;
import com.example.healthapp._class.DoctorAppointment;
import com.example.healthapp._class.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AppointmentDateChoosingActivity extends AppCompatActivity {
    private ImageButton btn_Back;
    private TextView doctorNameTextView;
    private TextView doctorSpecialityTextView;
    private TextView chooseDateTextView;
    private DatePicker chooseAppointmentDatePicker;
    private Button btn8h;
    private Button btn9h;
    private Button btn10h;
    private Button btn13h;
    private Button btn14h;
    private Button btn15h;
    private TextView chosenDateTextView;
    private ImageButton bookButton;
    private String doctorID;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    String userUID;
    String doctorName;
    String doctorSpeciality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_date_choosing);
        initUI();
        doctorID = getIntent().getStringExtra("doctorID");

        //GET USER UID
        mAuth = FirebaseAuth.getInstance();
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
        chosenDateTextView = findViewById(R.id.textView_ChoosenDate);
        chooseAppointmentDatePicker = findViewById(R.id.datePicker_ChooseAppointmentDate);
        // Lấy ngày mặc định từ DatePicker và hiển thị trên TextView
        //updateChosenDate();
        Calendar currentDate = Calendar.getInstance();
        updateChosenDate();
        // Thiết lập ngày tối thiểu cho DatePicker là ngày hiện tại
        chooseAppointmentDatePicker.setMinDate(currentDate.getTimeInMillis());
        // Thiết lập sự kiện lắng nghe cho DatePicker
        chooseAppointmentDatePicker.init(
                chooseAppointmentDatePicker.getYear(),
                chooseAppointmentDatePicker.getMonth(),
                chooseAppointmentDatePicker.getDayOfMonth(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    resetButton();
                    updateChosenDate();
                }
        );
        getDoctorInfo();

        // Thêm sự kiện cho nút "Đặt lịch"


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

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkButtonState())
                {
                    //      CHECK EXISTING APPOINTMENT AT CHOSEN DATE AND TIME
                    CollectionReference scheduleRef = FirebaseFirestore.getInstance().collection("Booking");

                    // Tạo query để lấy lịch của bác sĩ trong ngày đã chọn
                    Query query = scheduleRef
                            .whereEqualTo("patientID", userUID)
                            .whereEqualTo("appointmentDate", getChosenDate());

                    // Thực hiện truy vấn
                    query.get().addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    QuerySnapshot querySnapshot = task.getResult();
                                    if (querySnapshot != null) {
                                        // Lấy danh sách các khung giờ đã được hẹn
                                        //List<String> reservedTimes = new ArrayList<>();
                                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                            String reservedDate = document.getString("appointmentHour");
                                            String x = checkSelectedButton();
                                            if (reservedDate.equals(checkSelectedButton()))
                                            {
                                                Toast.makeText(v.getContext(), "Bạn đã có lịch vào giờ đã chọn! \nvui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                addBooking();
                                                addDoctorSchedule();
                                                Toast.makeText(v.getContext(), "Đặt lịch thành công!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(v.getContext(), PaymentChoosingActivity.class);


                                                intent.putExtra("doctorName", doctorName);
                                                intent.putExtra("doctorSpeciality", doctorSpeciality);
                                                startActivity(intent);
                                            }
                                        }

                                        // Cập nhật trạng thái của các nút
                                    }
                                } else {
                                    // Xử lý khi truy vấn thất bại
                                    Toast.makeText(v.getContext(), "Đã có lỗi xảy ra, Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                                }
                            });


                }
                else
                {
                    Toast.makeText(v.getContext(), "Vui lòng chọn giờ khám!", Toast.LENGTH_SHORT).show();
                }
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
    private boolean checkButtonState()
    {
        if(!btn8h.isSelected() && !btn9h.isSelected() && !btn10h.isSelected()
                && !btn13h.isSelected() && !btn14h.isSelected() && !btn15h.isSelected())
            return false;
        return true;
    }


    private String getChosenDate() {
        int day = chooseAppointmentDatePicker.getDayOfMonth();
        int month = chooseAppointmentDatePicker.getMonth();
        int year = chooseAppointmentDatePicker.getYear();

        // Tạo một đối tượng Calendar để định dạng ngày
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        // Định dạng ngày tháng năm và trả về dưới dạng String
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

    private void resetButton() {
        // Lấy giờ từ nút đã chọn
        btn8h.setEnabled(true);
        btn9h.setEnabled(true);
        btn10h.setEnabled(true);
        btn13h.setEnabled(true);
        btn14h.setEnabled(true);
        btn15h.setEnabled(true);
    }

    private void updateAppointmentButtons(String doctorId, String chosenDate) {
        // Reference đến collection DoctorSchedule
        CollectionReference scheduleRef = FirebaseFirestore.getInstance().collection("DoctorSchedule");

        // Tạo query để lấy lịch của bác sĩ trong ngày đã chọn
        Query query = scheduleRef
                .whereEqualTo("doctorID", doctorId)
                .whereEqualTo("date", chosenDate)
                .whereEqualTo("bookingStatus", true);

        // Thực hiện truy vấn
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    // Lấy danh sách các khung giờ đã được hẹn
                    //List<String> reservedTimes = new ArrayList<>();
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        String reservedTimes = document.getString("hour");
                        switch (reservedTimes)
                        {
                            case "8h - 9h":
                                btn8h.setEnabled(false);
                                break;
                            case "9h - 10h":
                                btn9h.setEnabled(false);
                                break;
                            case "10h - 11h":
                                btn10h.setEnabled(false);
                                break;
                            case "13h - 14h":
                                btn13h.setEnabled(false);
                                break;
                            case "14h - 15h":
                                btn14h.setEnabled(false);
                                break;
                            case "15h - 16h":
                                btn15h.setEnabled(false);
                                break;
                            default:
                                resetButton();
                                break;
                        }
                    }

                    // Cập nhật trạng thái của các nút
                }
            } else {
                // Xử lý khi truy vấn thất bại
                Toast.makeText(this, "Đã có lỗi xảy ra, Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String checkSelectedButton(){
        if(btn8h.isSelected())
            return "8h - 9h";
        else if(btn9h.isSelected())
            return "9h - 10h";
        else if(btn10h.isSelected())
            return "10h - 11h";
        else if(btn13h.isSelected())
            return "13h - 14h";
        else if(btn14h.isSelected())
            return "14h - 15h";
        else
            return "15h - 16h";
    }

    private void initUI() {
        btn_Back = findViewById(R.id.btn_Back);
        doctorNameTextView = findViewById(R.id.textView_DoctorName);
        doctorSpecialityTextView = findViewById(R.id.textView_DoctorSpeciality);
        chooseDateTextView = findViewById(R.id.textView_ChooseDate);
        chooseAppointmentDatePicker = findViewById(R.id.datePicker_ChooseAppointmentDate);
        btn8h = findViewById(R.id.btn_8h);
        btn9h = findViewById(R.id.btn_9h);
        btn10h = findViewById(R.id.btn_10h);
        btn13h = findViewById(R.id.btn_13h);
        btn14h = findViewById(R.id.btn_14h);
        btn15h = findViewById(R.id.btn_15h);
        chosenDateTextView = findViewById(R.id.textView_ChoosenDate);
        bookButton = findViewById(R.id.btn_Book);
    }
    private void updateChosenDate() {
        chosenDateTextView.setText("Ngày đã chọn: " + getChosenDate());
        //String doctorId = getIntent().getStringExtra("doctorId");
        //String chosenDate = getChosenDate();
        updateAppointmentButtons(doctorID, getChosenDate());
    }

    private void getDoctorInfo()
    {
        Intent intent = getIntent();
        if (intent != null) {
            doctorName = intent.getStringExtra("doctorName");
            doctorSpeciality = intent.getStringExtra("doctorSpeciality");

            // Hiển thị dữ liệu trong TextView
            String bookInfo = "Doctor Name: " + doctorName + "\nSpeciality: " + doctorSpeciality;
            doctorNameTextView.setText(doctorName);
            doctorSpecialityTextView.setText(doctorSpeciality);
        }
    }
    public void addBooking()
    {
        String chosenHour = checkSelectedButton();
        String chosenDate = getChosenDate();
        Appointment newAppointment = new Appointment(doctorID, userUID, chosenDate, chosenHour);

        CollectionReference BookingRef = FirebaseFirestore.getInstance().collection("Booking");

        BookingRef.add(newAppointment.toMap())
                .addOnSuccessListener(documentReference -> {
                    // Ghi dữ liệu thành công
                    String autoID = documentReference.getId();
                    Toast.makeText(AppointmentDateChoosingActivity.this, "Tạo hồ sơ thành công", Toast.LENGTH_SHORT).show();

                    // TODO: Thực hiện các hành động khác sau khi thêm thành công

                })
                .addOnFailureListener(e -> {
                    // Xử lý khi ghi dữ liệu thất bại
                    e.printStackTrace(); // In ra lỗi
                    Toast.makeText(AppointmentDateChoosingActivity.this, "Đã xảy ra lỗi. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                });
    }

    public void addDoctorSchedule()
    {
        String chosenHour = checkSelectedButton();
        String chosenDate = getChosenDate();
        DoctorAppointment doctorAppointment = new DoctorAppointment(doctorID, chosenDate, chosenHour, true);
        CollectionReference DoctorScheduleRef = FirebaseFirestore.getInstance().collection("DoctorSchedule");

        DoctorScheduleRef.add(doctorAppointment.toMap())
                .addOnSuccessListener(documentReference -> {
                    // Ghi dữ liệu thành công
                    String autoID = documentReference.getId();
                    Toast.makeText(AppointmentDateChoosingActivity.this, "Tạo hồ sơ thành công", Toast.LENGTH_SHORT).show();

                })
                .addOnFailureListener(e -> {
                    // Xử lý khi ghi dữ liệu thất bại
                    e.printStackTrace(); // In ra lỗi
                    Toast.makeText(AppointmentDateChoosingActivity.this, "Đã xảy ra lỗi. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                });
    }
}