package com.example.myapplication;




public class Record {
    String Object,Place,datet,timet,desct;

    public Record(String Object, String Place,  String datet, String timet, String desct)
    {
        this.Object = Object;
        this.Place = Place;
        this.datet = datet;
        this.timet = timet;
        this.desct = desct;
    }
    public Record()
    {

    }
    public String getEventt() {
        return Object;
    }

    public void setEventt(String eventt) {
        this.Object = Object;
    }

    public String getTopict() {
        return Place;
    }

    public void setTopict(String topict) {
        this.Place = Place;
    }

    public String getDatet() {
        return datet;
    }

    public void setDatet(String datet) {
        this.datet = datet;
    }

    public String getTimet() {
        return timet;
    }

    public void setTimet(String timet) {
        this.timet = timet;
    }

    public String getDesct() {
        return desct;
    }

    public void setDesct(String desct) {
        this.desct = desct;
    }

}


