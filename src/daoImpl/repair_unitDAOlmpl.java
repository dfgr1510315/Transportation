package daoImpl;

import Druid.DBPoolConnection;
import com.alibaba.druid.pool.DruidPooledConnection;
import dao.repair_unitDAO;
import model.repair_unit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class repair_unitDAOlmpl implements repair_unitDAO {

    @Override
    public List getRepair_unit(String SQL) {
        repair_unit reu;
        List<repair_unit> list = new ArrayList<>();
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        try {
            con = dbp.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                reu = new repair_unit();
                reu.setReuname(rs.getString("reuname"));
                reu.setReutel(rs.getString("reutel"));
                reu.setReuaddress(rs.getString("reuaddress"));
                list.add(reu);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (con!=null)
                try{
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
        return list;
    }

    @Override
    public boolean addRepair_unit(String reuname, String reutel, String reuaddress) {
        String SQL = "insert into repair_unit values(?,?,?)";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, reuname);
            qsql.setString(2, reutel);
            qsql.setString(3, reuaddress);
            state = qsql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (qsql!=null)
                try{
                    qsql.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if (con!=null)
                try{
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
        return state!=0;
    }

    @Override
    public List searchRepair_unit(String reuname) {
        String SQL = "select * from repair_unit where reuname like '%"+reuname+"%'";
        return getRepair_unit(SQL);
    }

    @Override
    public boolean deleteRepair_unit(String reuname) {
        String SQL = "delete FROM repair_unit WHERE reuname=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, reuname);
            state = qsql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (qsql!=null)
                try{
                    qsql.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if (con!=null)
                try{
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
        return state != 0;
    }

    @Override
    public boolean changeRepair_unit(String reuname, String reutel, String reuaddress, String oldname) {
        String SQL = "update repair_unit set reuname=?,reutel=?,reuaddress=? where reuname=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, reuname);
            qsql.setString(2, reutel);
            qsql.setString(3, reuaddress);
            qsql.setString(4, oldname);
            state = qsql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (qsql!=null)
                try{
                    qsql.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if (con!=null)
                try{
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
        return state != 0;
    }
}
