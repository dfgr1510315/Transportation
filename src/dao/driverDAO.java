package dao;

import model.driver;

public interface driverDAO {
    driver getDriver();

    boolean addDriver(String Licence_number,String name,String sex,String birth_year,String driving_type);

    driver searchDriver(String Licence_number);

    boolean deleteDriver(String Licence_number);

    boolean changeDriver(String Licence_number);
}
