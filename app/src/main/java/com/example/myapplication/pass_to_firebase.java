package com.example.myapplication;


import com.google.firebase.database.FirebaseDatabase;
public class pass_to_firebase {

    String name_p, class_p, div_p, roll_p, mobile_p, email_p;
   FirebaseDatabase data;

    pass_to_firebase(String name_p, String class_p, String div_p, String roll_p, String mobile_p, String email_p) {
        this.name_p = name_p;
        this.class_p = class_p;
        this.div_p = div_p;
        this.roll_p = roll_p;
        this.mobile_p = mobile_p;
        this.email_p = email_p;
    }


    public String getName_p() {
        return name_p;
    }

    public void setName_p(String name_p) {
        this.name_p = name_p;
    }

    public String getClass_p() {
        return class_p;
    }

    public void setClass_p(String class_p) {
        this.class_p = class_p;
    }

    public String getDiv_p() {
        return div_p;
    }

    public void setDiv_p(String div_p) {
        this.div_p = div_p;
    }

    public String getRoll_p() {
        return roll_p;
    }

    public void setRoll_p(String roll_p) {
        this.roll_p = roll_p;
    }

    public String getMobile_p() {
        return mobile_p;
    }

    public void setMobile_p(String mobile_p) {
        this.mobile_p = mobile_p;
    }

    public String getEmail_p() {
        return email_p;
    }

    public void setEmail_p(String email_p) {
        this.email_p = email_p;
    }


    public FirebaseDatabase getData() {
        return data;
    }

    public void setData(FirebaseDatabase data) {
        this.data = data;
    }
}
