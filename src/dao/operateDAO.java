package dao;

import java.util.List;

public interface operateDAO {
    List getListId(String SQL,String SQL_id);

    List getOperate(String SQL);

    int addOperate(String License_number, int car_id, String goods, String destination, String stime,String rtime,float driving_time);

    List searchOperate(int car_id);

    boolean deleteOperate(int opid);

    boolean changeOperate(int opid,String License_number, int car_id, String goods, String destination, String stime,String rtime,float driving_time);
}
