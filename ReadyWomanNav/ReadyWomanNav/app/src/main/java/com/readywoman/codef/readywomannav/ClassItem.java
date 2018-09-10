package com.readywoman.codef.readywomannav;

public class ClassItem {
    String cName;
    String cTerm;
    String cTime;
    String cPrice;
    String status;
    String center;

    public ClassItem(String cName, String cTerm, String cTime, String cPrice, String status, String center){
        this.cName = cName;
        this.cTerm = cTerm;
        this.cTime = cTime;
        this.cPrice = cPrice;
        this.status = status;
        this.center = center;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcTerm() {
        return cTerm;
    }

    public void setcTerm(String cTerm) {
        this.cTerm = cTerm;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getcPrice() {
        return cPrice;
    }

    public void setcPrice(String cPrice) {this.cPrice = cPrice;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCenter() {return center;}

    public void setCenter(String center) {this.center = center; }

}
