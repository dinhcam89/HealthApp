package com.example.healthapp._adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.R;
import com.example.healthapp._activity.AppointmentDateChoosingActivity;
import com.example.healthapp._activity.MainMenuActivity;
import com.example.healthapp._class.Appointment;
import com.example.healthapp._class.Doctor;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

public class DoctorAdapter extends  RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private List<Doctor> ListDoctors;

    public DoctorAdapter(List<Doctor> listDoctors) {
        ListDoctors = listDoctors;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = ListDoctors.get(position);
        if (doctor == null) {
            return;
        }
        holder.setIsRecyclable(false);
//        if (doctor.getAvailability() == false) {
//            holder.btn_Book.setEnabled(false);
//        }
        //holder.textView_Id.setText("Id: " + doctor.getDoctorID());
        holder.textView_DoctorName.setText(doctor.getDoctorName());
        holder.textView_DoctorSpeciality.setText(doctor.getDoctorSpeciality());
        //holder.textView_Price.setText(doctor.getDoctorExp());
        //holder.textView_Availability.setText("Availability: " + doctor.getAvailability());
    }

    @Override
    public int getItemCount() {
        if (ListDoctors != null) {
            return ListDoctors.size();
        }
        return 0;
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        //private TextView textView_Id;
        private TextView textView_DoctorName;
        private TextView textView_DoctorSpeciality;
        //private TextView textView_Price;
        //private TextView textView_D;
        private Button btn_Book;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            //textView_Id = itemView.findViewById(R.id.textView_Id);
            textView_DoctorName = itemView.findViewById(R.id.textView_DoctorName);
            textView_DoctorSpeciality = itemView.findViewById(R.id.textView_DoctorSpeciality);
            //textView_Availability = itemView.findViewById(R.id.textView_Availibility);
            //textView_Price = itemView.findViewById(R.id.textView_Price);
            btn_Book = itemView.findViewById(R.id.btn_Book);
            //FirebaseFirestore db = FirebaseFirestore.getInstance();

            btn_Book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(v.getContext(), AppointmentDateChoosingActivity.class);

                    // Truyền dữ liệu nếu cần thiết
                    intent.putExtra("doctorName", textView_DoctorName.getText().toString());
                    intent.putExtra("doctorSpeciality", textView_DoctorSpeciality.getText().toString());

                    // Khởi chạy Activity mới
                    v.getContext().startActivity(intent);*/
                    Doctor selectedDoctor = ListDoctors.get(getAdapterPosition());
                    if (selectedDoctor != null) {
                        // Chuyển sang AppointmentDateChoosingActivity khi chọn bác sĩ
                        Intent intent = new Intent(v.getContext(), AppointmentDateChoosingActivity.class);
                        intent.putExtra("doctorID", selectedDoctor.getDoctorID());
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}