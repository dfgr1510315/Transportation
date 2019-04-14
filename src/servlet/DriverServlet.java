package servlet;

import daoImpl.dirverDAOImpl;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DriverServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        dirverDAOImpl dl = new dirverDAOImpl();
        switch(action){
            case "get_driver":
                get_driver(response,dl);
                break;
            case "addDriver":
                addDriver(request,response,dl);
                break;
            case "change_driver":
                change_driver(request,response,dl);
                break;
            case "delete_driver":
                delete_driver(request,response,dl);
                break;
            case "searchDriver":
                searchDriver(request,response,dl);
                break;
        }
    }

    private void searchDriver(HttpServletRequest request,HttpServletResponse response,dirverDAOImpl dl)throws IOException{
        String name = request.getParameter("name");
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("driver_list",dl.searchDriver(name));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    private void delete_driver(HttpServletRequest request,HttpServletResponse response,dirverDAOImpl dl)throws IOException{
        PrintWriter out = response.getWriter();
        String license_number = request.getParameter("license_number");
        out.println(dl.deleteDriver(license_number));
        out.flush();
        out.close();
    }

    private void addDriver(HttpServletRequest request,HttpServletResponse response,dirverDAOImpl dl)throws IOException{
        PrintWriter out = response.getWriter();
        String license_number = request.getParameter("license_number");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birth_year = request.getParameter("birth_year");
        String dirving_type = request.getParameter("dirving_type");
        out.println(dl.addDriver(license_number,name,sex,birth_year,dirving_type));
        out.flush();
        out.close();
    }


    private void change_driver(HttpServletRequest request,HttpServletResponse response,dirverDAOImpl dl)throws IOException{
        PrintWriter out = response.getWriter();
        String license_number = request.getParameter("license_number");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birth_year = request.getParameter("birth_year");
        String dirving_type = request.getParameter("dirving_type");
        String old_no = request.getParameter("old_no");
        out.println(dl.changeDriver(old_no,license_number,name,sex,birth_year,dirving_type));
        out.flush();
        out.close();
    }

    private void get_driver(HttpServletResponse response,dirverDAOImpl dl) throws IOException{
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("driver_list",dl.getDriver("select * from driver order by License_number"));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
