package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthapp.R;
import com.example.healthapp._adapter.DoctorAdapter;
import com.example.healthapp._class.Doctor;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DoctorChoosingActivity extends AppCompatActivity {
    private RecyclerView rcv_Doctors;
    private DoctorAdapter adapter_Doctors;
    private List<Doctor> list_Doctors;
//    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_choosing);
        //initUI();
        //getData();
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });
    }


   /* private void initUI()
    {
       *//* db = FirebaseFirestore.getInstance();

        rcv_Doctors = findViewById(R.id.rcv_Doctors);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_Doctors.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_Doctors.addItemDecoration(dividerItemDecoration);

        list_Doctors = new ArrayList<>();
        adapter_Doctors = new DoctorAdapter(list_Doctors);

        rcv_Doctors.setAdapter(adapter_Doctors);*//*

    }
    private void getData()
    {
//        CollectionReference doctorsRef = db.collection("Doctor");
//
//        doctorsRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
//
//            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                if (documentSnapshot.exists()) {
//                    Doctor doctor = documentSnapshot.toObject(Doctor.class);
//                    list_Doctors.add(doctor);
//                }
//                adapter_Doctors.notifyDataSetChanged();
//            }
//
//            // Gọi phương thức để xử lý danh sách bác sĩ (ví dụ: đưa vào Adapter)
//        }).addOnFailureListener(e -> {
//            // Xử lý khi có lỗi xảy ra
//            // e.printStackTrace(); để in ra lỗi
//        });
       *//* CollectionReference doctorsRef = db.collection("BacSi");
        // Lắng nghe sự kiện thay đổi dữ liệu trong collection "Doctor"
        doctorsRef.addSnapshotListener((value, error) -> {
            if (error != null) {
                // Xử lý khi có lỗi xảy ra trong quá trình lắng nghe
                // error.printStackTrace(); để in ra lỗi
                return;
            }

            // Xóa dữ liệu cũ trong danh sách trước khi thêm dữ liệu mới
            list_Doctors.clear();

            // Lặp qua từng document và thêm vào danh sách
            for (QueryDocumentSnapshot documentSnapshot : value) {
                if (documentSnapshot.exists()) {
                    Doctor doctor = documentSnapshot.toObject(Doctor.class);
                    list_Doctors.add(doctor);
                }
            }

            // Cập nhật Adapter hoặc thực hiện các bước cần thiết để xử lý dữ liệu
            adapter_Doctors.notifyDataSetChanged();
        });
    }*/
}