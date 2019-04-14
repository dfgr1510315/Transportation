package dao;


import java.util.List;

public interface driverDAO {
    List getDriver(String SQL);

    boolean addDriver(String License_number,String name,String sex,String birth_year,String driving_type);

    List searchDriver(String name);

    boolean deleteDriver(String License_number);

    boolean changeDriver(String old_no,String License_number,String name,String sex,String birth_year,String driving_type);
}
