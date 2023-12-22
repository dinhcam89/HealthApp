package com.example.healthapp._class;

import java.util.HashMap;
import java.util.Map;

public class Doctor {
    private String doctorID;
    private String doctorName;
    private String doctorExp;
    private String doctorSpeciality;

    public Doctor() {}

    public Doctor(String doctorID, String doctorName, String doctorExp, String doctorSpeciality) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorExp = doctorExp;
        this.doctorSpeciality = doctorSpeciality;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorExp() {
        return doctorExp;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorExp(String doctorExp) {
        this.doctorExp = doctorExp;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("doctor-id", doctorID);
        result.put("name", doctorName);
        result.put("speciality", doctorSpeciality);
        result.put("exp", doctorExp);
        return result;
    }
}
