package daoImpl;

import Druid.DBPoolConnection;
import com.alibaba.druid.pool.DruidPooledConnection;
import dao.driverDAO;
import model.driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dirverDAOImpl implements driverDAO {
    @Override
    public List getDriver(String SQL){
        driver d;
        List<driver> list = new ArrayList<>();
        //String SQL = "select * from driver order by License_number";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        try {
            con = dbp.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                d = new driver();
                d.setLicense_number(rs.getString("License_number"));
                d.setName(rs.getString("name"));
                d.setSex(rs.getString("sex"));
                d.setBirth_year(rs.getString("birth_year"));
                d.setDirving_type(rs.getString("driving_type"));
                list.add(d);
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
    public boolean addDriver(String License_number, String name, String sex, String birth_year, String driving_type) {
        String SQL = "insert into driver values(?,?,?,?,?)";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, License_number);
            qsql.setString(2, name);
            qsql.setString(3, sex);
            qsql.setString(4, birth_year);
            qsql.setString(5, driving_type);
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
    public List searchDriver(String name) {
        String SQL = "select * from driver where name like '%"+name+"%' order by License_number";
        return getDriver(SQL);
    }

    @Override
    public boolean deleteDriver(String License_number) {
        String SQL = "delete FROM driver WHERE License_number=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, License_number);
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
    public boolean changeDriver(String old_no,String License_number,String name,String sex,String birth_year,String driving_type) {
        String SQL = "update driver set License_number=?,name=?,sex=?,birth_year=?,driving_type=? where License_number=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, License_number);
            qsql.setString(2, name);
            qsql.setString(3, sex);
            qsql.setString(4, birth_year);
            qsql.setString(5, driving_type);
            qsql.setString(6, old_no);
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
