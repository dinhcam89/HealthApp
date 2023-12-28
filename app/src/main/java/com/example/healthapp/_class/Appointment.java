package com.example.healthapp._class;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Appointment {
    private String appointmentID;
    private String doctorID;
    private String appointmentDate;
    private String appointmentHour;
    private String patientID;

    public Appointment(String appointmentID, String doctorID, String appointmentDate, String appointmentHour, String patientID) {
        this.appointmentID = appointmentID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
        this.appointmentHour = appointmentHour;
        this.patientID = patientID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentHour() {
        return appointmentHour;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentHour(String appointmentHour) {
        this.appointmentHour = appointmentHour;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    // Phương thức để thêm thông tin đặt lịch vào Firestore
    public void addToFirestore() {
        // Lấy reference đến collection DatLichKham
        CollectionReference appointmentRef = FirebaseFirestore.getInstance().collection("DatLichKham");

        // Thêm thông tin đặt lịch vào collection
        appointmentRef.document(appointmentID).set(this)
                .addOnSuccessListener(aVoid -> {
                    // Thêm thành công
                    // TODO: Xử lý khi thêm thành công (nếu cần)
                })
                .addOnFailureListener(e -> {
                    // Thêm thất bại
                    // TODO: Xử lý khi thêm thất bại (nếu cần)
                });
    }
}
