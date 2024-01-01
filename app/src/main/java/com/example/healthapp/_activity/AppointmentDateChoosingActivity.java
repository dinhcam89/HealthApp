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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_date_choosing);
        initUI();
        doctorID = getIntent().getStringExtra("doctorID");

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
        getDoctorInfor();

        // Thêm sự kiện cho nút "Đặt lịch"
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkButtonState())
                {

                }
                else
                {
                    Toast.makeText(v.getContext(), "Vui lòng chọn giờ khám!", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
        // Reference đến collection LichKhamCuaBacSi
        CollectionReference scheduleRef = FirebaseFirestore.getInstance().collection("LichKhamCuaBacSi");

        // Tạo query để lấy lịch của bác sĩ trong ngày đã chọn
        Query query = scheduleRef
                .whereEqualTo("doctorId", doctorId)
                .whereEqualTo("date", chosenDate);

        // Thực hiện truy vấn
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    // Lấy danh sách các khung giờ đã được hẹn
                    //List<String> reservedTimes = new ArrayList<>();
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        String reservedTimes = document.getString("time");
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

    private void resetAllButtons() {
        btn8h.setEnabled(true);
        btn9h.setEnabled(true);
        btn10h.setEnabled(true);
        btn13h.setEnabled(true);
        btn14h.setEnabled(true);
        btn15h.setEnabled(true);
        // Reset other buttons if needed
    }

    private void updateDoctorSchedule(String doctorId, String chosenDate, String chosenTime) {
        // Reference đến collection LichKhamCuaBacSi
        CollectionReference scheduleRef = FirebaseFirestore.getInstance().collection("LichKhamCuaBacSi");

        // Tạo query để tìm lịch cần cập nhật
        Query query = scheduleRef
                .whereEqualTo("doctorId", doctorId)
                .whereEqualTo("date", chosenDate)
                .whereEqualTo("time", chosenTime);

        // Thực hiện truy vấn
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    // Lấy ID của lịch cần cập nhật
                    String scheduleId = querySnapshot.getDocuments().get(0).getId();

                    // Cập nhật trạng thái lịch của bác sĩ thành "Đã được hẹn" tương ứng với false
                    scheduleRef.document(scheduleId).update("status", false)
                            .addOnSuccessListener(aVoid -> {
                                // Cập nhật thành công
                                Toast.makeText(this, "Đặt lịch thành công.", Toast.LENGTH_SHORT).show();
                                // TODO: Thực hiện thêm thông tin vào collection DatLichKham
                                // (Lấy id của người khám, id của bác sĩ, ngày và giờ đặt lịch)
                                addAppointmentToFirestore(/*userId,*/ doctorId, chosenDate, chosenTime);
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                // Cập nhật thất bại
                                Toast.makeText(this, "Lỗi khi cập nhật trạng thái lịch của bác sĩ.", Toast.LENGTH_SHORT).show();
                            });
                }
            } else {
                // Xử lý khi truy vấn thất bại
                Toast.makeText(this, "Lỗi khi cập nhật trạng thái lịch của bác sĩ.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addAppointmentToFirestore(String doctorId, String chosenDate, String chosenTime) {
        // Lấy ID của bác sĩ từ Intent hoặc từ nơi khác
        // String doctorId = "YourDoctorId"; // Bạn cần cung cấp ID của bác sĩ

        // Lấy ID của bệnh nhân từ collection Hoso
        String patientId = "YourPatientId"; // Bạn cần cung cấp ID của bệnh nhân
        getPatientIdFromFirestore(patientId, doctorId, chosenDate, chosenTime);
    }

    private void getPatientIdFromFirestore(String patientId, String doctorId, String chosenDate, String chosenTime) {
        // Reference đến collection Hoso
        CollectionReference patientRef = FirebaseFirestore.getInstance().collection("Hoso");

        // Tạo query để lấy thông tin của bệnh nhân
        Query query = patientRef.whereEqualTo("patientId", patientId);

        // Thực hiện truy vấn
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    // Lấy thông tin của bệnh nhân
                    // Ví dụ: String patientName = querySnapshot.getDocuments().get(0).getString("patientName");

                    // Tạo một đối tượng Appointment
                    Appointment appointment = new Appointment(null, doctorId, chosenDate, chosenTime, patientId);

                    // Thêm thông tin đặt lịch vào Firestore
                    appointment.addToFirestore();

                } else {
                    // Không tìm thấy thông tin của bệnh nhân
                    Toast.makeText(this, "Không tìm thấy thông tin của bệnh nhân.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Xử lý khi truy vấn thất bại
                Toast.makeText(this, "Lỗi khi lấy thông tin của bệnh nhân.", Toast.LENGTH_SHORT).show();
            }
        });
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
        //String doctorId = getIntent().getStringExtra("doctorId");
        //String chosenDate = getChosenDate();
        updateAppointmentButtons(doctorID, formattedDate);
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