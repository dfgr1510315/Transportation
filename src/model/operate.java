package model;

public class operate {
    private int opid;
    private String license_number;
    private int car_id;
    private String goods;
    private String destination;
    private String stime;
    private String rtime;
    private String driving_time;

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getRtime() {
        return (rtime);
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getDriving_time() {
        return driving_time;
    }

    public void setDriving_time(String driving_time) {
        this.driving_time = driving_time;
    }

    public int getOpid() {
        return opid;
    }

    public void setOpid(int opid) {
        this.opid = opid;
    }
}
