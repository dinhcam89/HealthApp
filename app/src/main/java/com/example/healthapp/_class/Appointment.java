package com.example.healthapp._class;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Appointment {
    private String doctorID;
    private String patientID;
    private String appointmentDate;
    private String appointmentHour;

    public Appointment() {
    }

    public Appointment(String doctorID, String patientID, String appointmentDate, String appointmentHour) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.appointmentDate = appointmentDate;
        this.appointmentHour = appointmentHour;

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
        result.put("doctorID", doctorID);
        result.put("patientID", patientID);
        result.put("appointmentDate", appointmentDate);
        result.put("appointmentHour", appointmentHour);

        return result;
    }
}
