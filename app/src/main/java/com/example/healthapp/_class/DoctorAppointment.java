package com.example.healthapp._class;

import java.util.HashMap;
import java.util.Map;

public class DoctorAppointment {
    private String doctorID;
    private String date;
    private boolean bookingStatus;
    private String hour;

    public DoctorAppointment() {}

    public DoctorAppointment(String doctorID, String date, boolean bookingStatus, String hour) {
        this.doctorID = doctorID;
        this.date = date;
        this.bookingStatus = bookingStatus;
        this.hour = hour;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDate() {
        return date;
    }

    public boolean getBookingStatus() {
        return bookingStatus;
    }

    public String getHour() {
        return hour;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("doctorID", doctorID);
        result.put("date", date);
        result.put("bookingStatus", bookingStatus);
        result.put("hour", hour);

        return result;
    }
}
