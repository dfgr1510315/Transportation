package daoImpl;

import Druid.DBPoolConnection;
import com.alibaba.druid.pool.DruidPooledConnection;
import dao.carDAO;
import model.car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class carDAOlmpl implements carDAO {

    @Override
    public List getCar(String SQL) {
        car c;
        List<car> list = new ArrayList<>();
        //String SQL = "select * from driver order by License_number";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        try {
            con = dbp.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                c = new car();
                c.setCar_id(rs.getInt("car_id"));
                c.setCar_type(rs.getString("car_type"));
                c.setColor(rs.getString("color"));
                c.setMax_guest(rs.getInt("max_guest"));
                list.add(c);
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
    public int addCar(String car_type, String color, int max_guest) {
        String SQL = "insert into car (car_type,color,max_guest)values(?,?,?)";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int car_id = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, car_type);
            qsql.setString(2, color);
            qsql.setInt(3, max_guest);
            qsql.executeUpdate();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID() car_id");
            while (rs.next()) {
                car_id = rs.getInt("car_id");
            }
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
        return car_id;
    }

    @Override
    public List searchCar(int car_id) {
        String SQL = "select * from car where car_id like '%"+car_id+"%' order by car_id";
        return getCar(SQL);
    }

    @Override
    public boolean deleteCar(int car_id) {
        String SQL = "delete from car where car_id=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setInt(1, car_id);
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
    public boolean changeCar(int car_id, String car_type, String color, int max_guest) {
        String SQL = "update car set car_type=?,color=?,max_guest=? where car_id=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, car_type);
            qsql.setString(2, color);
            qsql.setInt(3, max_guest);
            qsql.setInt(4, car_id);
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
