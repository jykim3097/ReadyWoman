package com.readywoman.codef.readywomannav;

public class InfoClass {
    public int _id;
    public String name;
    public String term;
    public String time;
    public String price;
    public String status;
    public String center;

    //생성자
    public InfoClass(){}

    /**
     * 실질적으로 값을 입력할 때 사용되는 생성자(getter and setter)
     * @param _id       테이블 아이디
     * @param name      이름
     * @param term      교육기간
     * @param time      요일/시간
     * @param price     수강료
     * @param status    정원
     * @param center    센터
     */
    public InfoClass(int _id, String name, String term, String time, String price, String status, String center) {
        this._id = _id;
        this.name = name;
        this.term = term;
        this.time = time;
        this.price = price;
        this.status = status;
        this.center = center;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}
