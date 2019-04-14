package dao;

import java.util.List;

public interface accidentDAO {

    List getAccident(String SQL);

    int addAccident(int acid,String actime,String acaddress,String acreason,int acdie);

    List searchAccident(int acid);

    boolean deleteAccident(int aid);

    boolean changeAccident(int acid,String actime,String acaddress,String acreason,int acdie,int aid);
}
