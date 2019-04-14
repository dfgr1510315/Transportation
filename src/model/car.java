package model;

public class car {
    private int car_id;
    private String car_type;
    private String color;
    private int max_guest;

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMax_guest() {
        return max_guest;
    }

    public void setMax_guest(int max_guest) {
        this.max_guest = max_guest;
    }
}
