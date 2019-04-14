package dao;

import java.util.List;

public interface carDAO {
    List getCar(String SQL);

    int addCar(String car_type,String color,int max_guest);

    List searchCar(int car_id);

    boolean deleteCar(int car_id);

    boolean changeCar(int car_id,String car_type,String color,int max_guest);
}
