package com.readywoman.codef.readywomannav;

public class ClassItem {
    String cName;
    String cTerm;
    String cTime;
    int cPrice;
    String center;
    int resId;

    public ClassItem(String cName, String cTerm, String cTime, int cPrice, String center, int resId){
        this.cName = cName;
        this.cTerm = cTerm;
        this.cTime = cTime;
        this.cPrice = cPrice;
        this.center = center;
        this.resId = resId;
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

    public int getcPrice() {
        return cPrice;
    }

    public void setcPrice(int cPrice) {
        this.cPrice = cPrice;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

}
