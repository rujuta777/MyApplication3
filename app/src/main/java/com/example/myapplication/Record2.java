package com.example.myapplication;

public class Record2
{
   String eventt,topict, conductort, venuet,datet,timet,desct;

    public Record2(String eventt, String topict, String conductort, String venuet, String datet, String timet, String desct)
    {
        this.eventt = eventt;
        this.topict = topict;
        this.conductort = conductort;
        this.venuet = venuet;
        this.datet = datet;
        this.timet = timet;
        this.desct = desct;
    }
    public Record2()
    {

    }
    public String getEventt() {
        return eventt;
    }

    public void setEventt(String eventt) {
        this.eventt = eventt;
    }

    public String getTopict() {
        return topict;
    }

    public void setTopict(String topict) {
        this.topict = topict;
    }

    public String getConductort() {
        return conductort;
    }

    public void setConductort(String conductort) {
        this.conductort = conductort;
    }

    public String getVenuet() {
        return venuet;
    }

    public void setVenuet(String venuet) {
        this.venuet = venuet;
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
