package com.example.healthapp._adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.R;
import com.example.healthapp._class.Appointment;
import com.example.healthapp._class.Doctor;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.protobuf.StringValue;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;
    private Context context;
    FirebaseFirestore db;
    public AppointmentAdapter(List<Appointment> appointmentList, Context context) {
        this.appointmentList = appointmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        String doctorName, doctorSpeciality;
        db = FirebaseFirestore.getInstance();
        db.collection("Doctor").document(appointment.getDoctorID())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Dữ liệu của bác sĩ được tìm thấy
                        Doctor doctor = documentSnapshot.toObject(Doctor.class);
                        holder.textView_DoctorName.setText(doctor.getDoctorName());
                        holder.textView_DoctorSpeciality.setText(doctor.getDoctorSpeciality());
                        // Xử lý dữ liệu bác sĩ ở đây
                    } else {
                        // Bác sĩ không tồn tại
                    }
                })
                .addOnFailureListener(e -> {
                    // Xử lý khi truy vấn thất bại
                });
        holder.textView_AppointmentDate.setText(appointment.getAppointmentHour() + " ngày " + appointment.getAppointmentDate());
        if(appointment.isPay())
        {
            holder.textView_PaymentMethod.setText("Đã thanh toán");
        }
        if(!appointment.isPay())
        {
            holder.textView_PaymentMethod.setText("Chưa thanh toán");
        }

    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView textView_DoctorName, textView_DoctorSpeciality, textView_PaymentMethod, textView_AppointmentDate;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_DoctorName = itemView.findViewById(R.id.textView_DoctorName);
            textView_DoctorSpeciality = itemView.findViewById(R.id.textView_DoctorSpeciality);
            textView_PaymentMethod = itemView.findViewById(R.id.textView_PaymentMethod);
            textView_AppointmentDate = itemView.findViewById(R.id.textView_AppointmentDate);

            // Initialize other views if needed
        }
    }
}
