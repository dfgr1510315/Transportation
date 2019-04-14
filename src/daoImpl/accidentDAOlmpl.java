package daoImpl;

import Druid.DBPoolConnection;
import com.alibaba.druid.pool.DruidPooledConnection;
import dao.accidentDAO;
import model.accident;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class accidentDAOlmpl implements accidentDAO {

    @Override
    public List getAccident(String SQL) {
        accident ac;
        List<accident> list = new ArrayList<>();
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        try {
            con = dbp.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                ac = new accident();
                ac.setAid(rs.getInt("aid"));
                ac.setAcid(rs.getInt("acid"));
                ac.setActime(rs.getString("actime"));
                ac.setAcaddress(rs.getString("acaddress"));
                ac.setAcreason(rs.getString("acreason"));
                ac.setAcdie(rs.getInt("acdie"));
                list.add(ac);
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
    public int addAccident(int acid, String actime, String acaddress, String acreason, int acdie) {
        String SQL = "insert into accident (acid,actime,acaddress,acreason,acdie)values(?,?,?,?,?)";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int aid = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setInt(1, acid);
            qsql.setString(2, actime);
            qsql.setString(3, acaddress);
            qsql.setString(4, acreason);
            qsql.setInt(5, acdie);
            qsql.executeUpdate();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID() aid");
            while (rs.next()) {
                aid = rs.getInt("aid");
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
        return aid;
    }

    @Override
    public List searchAccident(int acid) {
        String SQL = "select * from accident where acid="+acid;
        return getAccident(SQL);
    }

    @Override
    public boolean deleteAccident(int aid) {
        String SQL = "delete FROM accident WHERE aid=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con =null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setInt(1, aid);
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
    public boolean changeAccident(int acid, String actime, String acaddress, String acreason, int acdie, int aid) {
        String SQL = "update accident set acid=?,actime=?,acaddress=?,acreason=?,acdie=? where aid=?";
        DBPoolConnection dbp = DBPoolConnection.getInstance();
        DruidPooledConnection con = null;
        PreparedStatement qsql = null;
        int state = 0;
        try {
            con = dbp.getConnection();
            qsql = con.prepareStatement(SQL);
            qsql.setInt(1, acid);
            qsql.setString(2, actime);
            qsql.setString(3, acaddress);
            qsql.setString(4, acreason);
            qsql.setInt(5, acdie);
            qsql.setInt(6, aid);
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
