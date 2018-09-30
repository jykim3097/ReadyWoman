package com.readywoman.codef.readywomannav;

public class ClassItem {
    private String cName;
    private String cTerm;
    private String cTime;
    private String cPrice;
    private String status;
    private String center;

    public ClassItem(String cName, String cTerm, String cTime, String cPrice, String status, String center){
        this.cName = cName;
        this.cTerm = cTerm;
        this.cTime = cTime;
        this.cPrice = cPrice;
        this.status = status;
        this.center = center;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
    public void setcTerm(String cTerm) {
        this.cTerm = cTerm;
    }
    public void setcTime(String cTime) {
        this.cTime = cTime;
    }
    public void setcPrice(String cPrice) {
        this.cPrice = cPrice;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCenter(String center) {
        this.center = center;
    }

    public String getcName() {
        return cName;
    }
    public String getcTerm() {
        return cTerm;
    }
    public String getcTime() {
        return cTime;
    }
    public String getcPrice() {
        return cPrice;
    }
    public String getStatus() {
        return status;
    }
    public String getCenter() {
        return center;
    }
}
