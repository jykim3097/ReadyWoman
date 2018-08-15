package org.techtown.jsoup_rw;

public class Program {
    private String center; //센터명
    private String cName; //강좌명
    private String cTerm; //교육기간
    private String cTime; //교육시간, 요일
    private String cPrice; //수강료
    private String cState; //강좌상태
    private String cURL;

    public Program(String center, String cName, String cTerm, String cTime, String cPrice, String cState, String cURL) {
        this.center = center;
        this.cName = cName;
        this.cTerm = cTerm;
        this.cTime = cTime;
        this.cPrice = cPrice;
        this.cState = cState;
        this.cURL = cURL;
    }

    public String getCenter() {
        return center;
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
    public String getcState() {
        return cState;
    }
    public String getcURL(){
        return cURL;
    }
}
