package com.example.myapplication;

public class Record4
{
    String Name;
    String Notice;


    private Record4(){}
    public Record4(String name, String notice,String time)
    {
        this.Name = name;
        this.Notice = notice;
        this.time=time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;
}
