package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.healthapp.R;
import com.example.healthapp._class.Doctor;
import com.example.healthapp._class.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText editTextUserName, editTextPhoneNumber, editTextDateOfBirth, editTextInsuranceID, editTextEmail;
    private RadioGroup radioGroupGioiTinh;
    private RadioButton radioButtonFemale, radioButtonMale, radioButtonUnknown;
    private Button btnCreateProfile;
    private String userUid;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        initUI();
        Intent intent = getIntent();

        // Kiểm tra xem Intent có dữ liệu "user_uid" hay không
        if (intent.hasExtra("user_Uid")) {
            // Lấy UID từ Intent
            userUid = intent.getStringExtra("user_Uid");

            // Bạn có thể sử dụng UID theo nhu cầu của mình ở đây
        }


        String profileID = "profile01";
        String userName = editTextUserName.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String dateOfBirth = editTextDateOfBirth.getText().toString();
        String insuranceID = editTextInsuranceID.getText().toString();
        String email = editTextEmail.getText().toString();
        String gender = "male";
        FirebaseAuth auth = FirebaseAuth.getInstance();


        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Profile profile = new Profile(userUid, profileID, userName, phoneNumber, dateOfBirth, insuranceID, email, gender);
                Map<String, Object> profileValues = profile.toMap();

                db.collection("HoSo")
                        .document("hoso-6")
                        .set(profile)
                        .addOnSuccessListener(aVoid -> {
                            // Ghi dữ liệu thành công
                        })
                        .addOnFailureListener(e -> {
                            // Xử lý khi ghi dữ liệu thất bại
                            // e.printStackTrace(); để in ra lỗi
                        });
            }
        });
    }

    private void initUI() {
        editTextUserName = findViewById(R.id.editText_UserName);
        editTextPhoneNumber = findViewById(R.id.editText_PhoneNumber);
        editTextDateOfBirth = findViewById(R.id.editText_DateOfBirth);
        editTextInsuranceID = findViewById(R.id.editText_InsuranceID);
        editTextEmail = findViewById(R.id.editText_Email);

        radioGroupGioiTinh = findViewById(R.id.radio_gioitinh);
        radioButtonFemale = findViewById(R.id.radioButton_Female);
        radioButtonMale = findViewById(R.id.radioButton_Male);
        radioButtonUnknown = findViewById(R.id.radioButton_Unknown);

        btnCreateProfile = findViewById(R.id.btn_CreateProfile);
        db = FirebaseFirestore.getInstance();

        // Bạn có thể thêm sự kiện lắng nghe cho btnCreateProfile ở đây
        // ví dụ: btnCreateProfile.setOnClickListener(new View.OnClickListener() {...});
    }

    private void createProfile()
    {

    }

}