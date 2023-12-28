package com.example.healthapp._class;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    private String uid;
    private String profileID;
    private String name;
    private String phoneNumber;
    private String dateOfBirth;
    private String insuranceID;
    private String email;
    private String gender;

    public Profile() {}

    public Profile(String uid, String profileID, String name, String phoneNumber, String dateOfBirth, String insuranceID, String email, String gender) {
        this.uid = uid;
        this.profileID = profileID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.insuranceID = insuranceID;
        this.email = email;
        this.gender = gender;
    }

    public String getUid() {
        return uid;
    }

    public String getProfileID() {
        return profileID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("profileID", profileID);
        result.put("name", name);
        result.put("phoneNumber", phoneNumber);
        result.put("dateOfBirth", dateOfBirth);
        result.put("insuranceID", insuranceID);
        result.put("email", email);
        result.put("gender", gender);

        return result;
    }

}
