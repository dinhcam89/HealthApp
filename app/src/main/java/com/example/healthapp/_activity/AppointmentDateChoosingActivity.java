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
import android.widget.Toast;

import com.example.healthapp.R;
import com.example.healthapp._class.Appointment;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

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
        //getDoctorInfor();

// Thêm sự kiện cho nút "Đặt lịch"
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin đã chọn
                String doctorId = getIntent().getStringExtra("doctorId");
                String chosenDate = getChosenDate();
                String chosenTime = getChosenTime();

                // Kiểm tra và đặt lịch
                checkDoctorSchedule(doctorId, chosenDate, chosenTime);
            }
        });

    }

    private String getChosenDate() {
        int day = chooseAppointmentDatePicker.getDayOfMonth();
        int month = chooseAppointmentDatePicker.getMonth();
        int year = chooseAppointmentDatePicker.getYear();

        // Tạo một đối tượng Calendar để định dạng ngày
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        // Định dạng ngày tháng năm và trả về dưới dạng String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

    // Thêm phương thức để lấy giờ đã chọn
    private String getChosenTime() {
        // Ở đây bạn cần xác định làm thế nào để lấy giờ đã chọn từ nút được chọn (btn8h, btn9h, ...)
        // Đoạn mã sau là một ví dụ giả định, bạn có thể thay đổi nó tùy thuộc vào cách bạn cài đặt nút
        if (btn8h.isSelected()) {
            return "8h - 9h";
        } else if (btn9h.isSelected()) {
            return "9h - 10h";
        } else if (btn10h.isSelected()) {
            return "10h - 11h";
        } else if (btn13h.isSelected()) {
            return "13h - 14h";
        } else if (btn14h.isSelected()) {
            return "14h - 15h";
        } else if (btn15h.isSelected()) {
            return "15h - 16h";
        }

        // Nếu không có nút nào được chọn, bạn có thể xử lý theo ý của bạn, ví dụ: trả về giá trị mặc định
        return "8h - 9h";
    }


    private void checkDoctorSchedule(String doctorId, String chosenDate, String chosenTime) {
        // Reference đến collection LichKhamCuaBacSi
        CollectionReference scheduleRef = FirebaseFirestore.getInstance().collection("LichKhamCuaBacSi");

        // Tạo query để kiểm tra xem có lịch nào trống không
        Query query = scheduleRef
                .whereEqualTo("doctorId", doctorId)
                .whereEqualTo("date", chosenDate)
                .whereEqualTo("time", chosenTime)
                .whereEqualTo("status", "Trống lịch");

        // Thực hiện truy vấn
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    // Có lịch trống, bạn có thể thực hiện đặt lịch ở đây
                    // TODO: Thực hiện đặt lịch và cập nhật Firestore

                    // Sau khi đặt lịch, cập nhật trạng thái lịch của bác sĩ thành "Đã được hẹn"
                    updateDoctorSchedule(doctorId, chosenDate, chosenTime);
                } else {
                    // Không có lịch trống, hiển thị thông báo cho người dùng
                    Toast.makeText(this, "Bác sĩ không còn lịch trống trong khung giờ đã chọn.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Xử lý khi truy vấn thất bại
                Toast.makeText(this, "Lỗi khi kiểm tra lịch khám của bác sĩ.", Toast.LENGTH_SHORT).show();
            }
        });
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

                    // Cập nhật trạng thái lịch của bác sĩ thành "Đã được hẹn"
                    scheduleRef.document(scheduleId).update("status", "Đã được hẹn")
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
    /*private void getDoctorInfor()
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
    }*/
}