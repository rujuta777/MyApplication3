package com.example.myapplication;

import android.content.Intent;
import android.icu.text.AlphabeticIndex;

public class Record1
{
    String namet;
    String classt;
    String divt;
    String rollt;
    String passt;
    String mobilet;
    String emailt;
    String attendance;



    public Record1()
    {

    }
    public Record1(String namet, String classt, String divt, String rollt, String passt, String mobilet, String emailt, String attendance)
    {
        this.namet = namet;
        this.classt = classt;
        this.divt = divt;
        this.rollt = rollt;
        this.passt = passt;
        this.mobilet = mobilet;
        this.emailt = emailt;
        this.attendance=attendance;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getNamet() {
        return namet;
    }

    public void setNamet(String namet) {
        this.namet = namet;
    }

    public String getClasst() {
        return classt;
    }

    public void setClasst(String classt) {
        this.classt = classt;
    }

    public String getDivt() {
        return divt;
    }

    public void setDivt(String divt) {
        this.divt = divt;
    }

    public String getRollt() {
        return rollt;
    }

    public void setRollt(String rollt) {
        this.rollt = rollt;
    }

    public String getPasst() {
        return passt;
    }

    public void setPasst(String passt) {
        this.passt = passt;
    }

    public String getMobilet() {
        return mobilet;
    }

    public void setMobilet(String mobilet) {
        this.mobilet = mobilet;
    }

    public String getEmailt() {
        return emailt;
    }

    public void setEmailt(String emailt) {
        this.emailt = emailt;
    }
}

