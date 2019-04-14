package daoImpl;

import Druid.DBPoolConnection;
import com.alibaba.druid.pool.DruidPooledConnection;
import dao.operateDAO;
import model.operate;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class operateDAOlmpl implements operateDAO {

    @Override
    public List getListId(String SQL,String SQL_id) {
        List<String> list = new ArrayList<>();
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        try {
            con = dbp.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                list.add(rs.getString(SQL_id));
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
    public List getOperate(String SQL) {
        operate o;
        List<operate> list = new ArrayList<>();
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        try {
            con = dbp.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                o = new operate();
                o.setOpid(rs.getInt("opid"));
                o.setLicense_number(rs.getString("oplicense_number"));
                o.setCar_id(rs.getInt("opcarid"));
                o.setDestination(rs.getString("opdestination"));
                o.setGoods(rs.getString("opgoods"));
                o.setStime(rs.getDate("opstime").toString());
                o.setRtime(rs.getDate("oprtime").toString());
                o.setDriving_time(rs.getString("opdriving_time"));
                //System.out.println(o.getDriving_time());
                list.add(o);
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
    public int addOperate(String License_number, int car_id, String goods, String destination, String stime, String rtime, float driving_time) {
        String SQL = "insert into operate (oplicense_number,opcarid,opgoods,opdestination,opstime,oprtime,opdriving_time)values(?,?,?,?,?,?,?)";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int opid = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, License_number);
            qsql.setInt(2, car_id);
            qsql.setString(3, goods);
            qsql.setString(4, destination);
            qsql.setDate(5, new java.sql.Date(sdf.parse(stime).getTime()));
            qsql.setDate(6, new java.sql.Date(sdf.parse(rtime).getTime()));
            qsql.setFloat(7, driving_time);
            qsql.executeUpdate();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID() opid");
            while (rs.next()) {
                opid = rs.getInt("opid");
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
        return opid;
    }

    @Override
    public List searchOperate(int opid) {
        String SQL = "select * from operate where opcarid="+opid;
        return getOperate(SQL);
    }

    @Override
    public boolean deleteOperate(int opid) {
        String SQL = "delete FROM operate WHERE opid=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setInt(1, opid);
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
    public boolean changeOperate(int opid, String License_number, int car_id, String goods, String destination, String stime, String rtime, float driving_time) {
        String SQL = "update operate set oplicense_number=?,opcarid=?,opgoods=?,opdestination=?,opstime=?,oprtime=?,opdriving_time=? where opid=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int state = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setString(1, License_number);
            qsql.setInt(2, car_id);
            qsql.setString(3, goods);
            qsql.setString(4, destination);
            qsql.setDate(5, new java.sql.Date(sdf.parse(stime).getTime()));
            qsql.setDate(6, new java.sql.Date(sdf.parse(rtime).getTime()));
            qsql.setFloat(7, driving_time);
            qsql.setInt(8, opid);
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
