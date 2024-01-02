package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.R;
import com.example.healthapp._class.Doctor;
import com.example.healthapp._class.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Map;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText editTextUserName, editTextPhoneNumber, editTextDateOfBirth, editTextInsuranceID, editTextEmail;
    private RadioGroup radioGroupGender;
    private Button btnCreateProfile;
    private String userUid;
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    private String gender = "";
    private String userName;
    private String phoneNumber;
    private String dateOfBirth;
    private String insuranceID;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        initUI();
        Intent intent = getIntent();
        mAuth = FirebaseAuth.getInstance();

        // Get User UID
        mAuth.addAuthStateListener(auth -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                userUid = user.getUid();
            } else {
                // Đăng xuất, xử lý theo ý muốn
            }
        });
        // Get email from SignIn Activity
        if (intent.hasExtra("email")) {
            editTextEmail.setText(intent.getStringExtra("email"));
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radId = radioGroupGender.getCheckedRadioButtonId();
                if (radId == R.id.radioButton_Male) {
                    gender = "Nam";
                } else if (radId == R.id.radioButton_Female) {
                    gender = "Nữ";
                } else {
                    gender = "Chưa biết";
                }
                // Lấy reference đến collection "HoSo"
                CollectionReference hoSoRef = db.collection("UserProfile");

                // Lấy các giá trị từ EditTexts
                userName = editTextUserName.getText().toString().trim();
                phoneNumber = editTextPhoneNumber.getText().toString().trim();
                dateOfBirth = editTextDateOfBirth.getText().toString().trim();
                insuranceID = editTextInsuranceID.getText().toString().trim();
                email = editTextEmail.getText().toString().trim();

                // TODO: Lấy các giá trị khác theo cách bạn muốn

                // Tạo một đối tượng Profile
                Profile profile = new Profile(userUid, userName, phoneNumber, dateOfBirth, insuranceID, email, gender);

                // Thêm dữ liệu vào Firestore với ID được tạo ngẫu nhiên
                hoSoRef.add(profile.toMap())
                        .addOnSuccessListener(documentReference -> {
                            // Ghi dữ liệu thành công
                            String generatedProfileId = documentReference.getId();
                            Toast.makeText(CreateProfileActivity.this, "Tạo hồ sơ thành công", Toast.LENGTH_SHORT).show();

                            // TODO: Thực hiện các hành động khác sau khi thêm thành công

                            Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            // Xử lý khi ghi dữ liệu thất bại
                            e.printStackTrace(); // In ra lỗi
                            Toast.makeText(CreateProfileActivity.this, "Đã xảy ra lỗi. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
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

        radioGroupGender = findViewById(R.id.radioGroupGender);

        btnCreateProfile = findViewById(R.id.btn_CreateProfile);
        db = FirebaseFirestore.getInstance();

        // Bạn có thể thêm sự kiện lắng nghe cho btnCreateProfile ở đây
        // ví dụ: btnCreateProfile.setOnClickListener(new View.OnClickListener() {...});
    }

    private void createProfile()
    {

    }

}