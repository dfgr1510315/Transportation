package dao;

import java.util.List;

public interface repair_unitDAO {
    List getRepair_unit(String SQL);

    boolean addRepair_unit(String reuname,String reutel,String reuaddress);

    List searchRepair_unit(String reuname);

    boolean deleteRepair_unit(String reuname);

    boolean changeRepair_unit(String reuname,String reutel,String reuaddress,String oldname);
}
