package com.example.healthapp._class;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.PropertyName;

import java.util.HashMap;
import java.util.Map;

public class Appointment {
    private String doctorID;
    private String patientID;
    private String appointmentDate;
    private String appointmentHour;
    private boolean isPay;
    public Appointment() {
    }

    public Appointment(String doctorID, String patientID, String appointmentDate, String appointmentHour, boolean isPay) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.appointmentDate = appointmentDate;
        this.appointmentHour = appointmentHour;
        this.isPay = isPay;
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
    @PropertyName("isPay")
    public boolean isPay() {
        return isPay;
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
    @PropertyName("isPay")
    public void setPay(boolean pay) {
        isPay = pay;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("doctorID", doctorID);
        result.put("patientID", patientID);
        result.put("appointmentDate", appointmentDate);
        result.put("appointmentHour", appointmentHour);
        result.put("isPay", isPay);

        return result;
    }
}
