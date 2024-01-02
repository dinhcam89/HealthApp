package com.example.healthapp._activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.healthapp.R;
import com.example.healthapp._adapter.DoctorAdapter;
import com.example.healthapp._class.Doctor;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class DoctorChoosingActivity extends AppCompatActivity implements DoctorAdapter.OnDoctorClickListener {
    private RecyclerView rcv_Doctors;
    private DoctorAdapter adapter_Doctors;
    private List<Doctor> list_Doctors;
    private SearchView searchView;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_choosing);
        initUI();
        getData();
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return false;
            }
        });
    }

    private void filterlist(String newText) {
        List<Doctor> filteredList = new ArrayList<>();
        for(Doctor doctor : list_Doctors)
        {
            if(doctor.getDoctorName().toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList.add(doctor);
            }
        }
        if(filteredList.isEmpty())
        {
            //Toast.makeText(this, "Không tìm thấy bác sĩ", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter_Doctors.setFilteredList(filteredList);
        }
    }



    private void initUI()
    {
        db = FirebaseFirestore.getInstance();

        rcv_Doctors = findViewById(R.id.rcv_Doctors);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_Doctors.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        rcv_Doctors.addItemDecoration(dividerItemDecoration);

        list_Doctors = new ArrayList<>();
        // Chuyển interface để lắng nghe sự kiện chọn bác sĩ
        adapter_Doctors = new DoctorAdapter(list_Doctors, (DoctorAdapter.OnDoctorClickListener) this);

        rcv_Doctors.setAdapter(adapter_Doctors);
        searchView = findViewById(R.id.search_bar);

    }
    private void getData()
    {
        CollectionReference doctorsRef = db.collection("Doctor");

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
    }

    // Phương thức từ interface để lắng nghe sự kiện khi người dùng chọn một bác sĩ
    @Override
    public void onDoctorClick(String doctorID) {
        // Chuyển sang AppointmentDateChoosingActivity và truyền ID của bác sĩ
        Intent intent = new Intent(this, AppointmentDateChoosingActivity.class);
        intent.putExtra("doctorID", doctorID);
        startActivity(intent);
    }

}