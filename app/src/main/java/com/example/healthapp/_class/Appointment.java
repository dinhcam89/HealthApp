package com.example.healthapp._class;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Appointment {
    private String doctorID;
    private String appointmentDate;
    private String appointmentHour;
    private String patientID;

    public Appointment(String doctorID, String appointmentDate, String appointmentHour, String patientID) {
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
        this.appointmentHour = appointmentHour;
        this.patientID = patientID;
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

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("patientID", patientID);
        result.put("doctorID", doctorID);
        result.put("appointmentDate", appointmentDate);
        result.put("appointmentHour", appointmentHour);

        return result;
    }
}
