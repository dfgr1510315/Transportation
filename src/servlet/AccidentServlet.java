package servlet;

import daoImpl.accidentDAOlmpl;
import daoImpl.operateDAOlmpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AccidentServlet")
public class AccidentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        accidentDAOlmpl ac = new accidentDAOlmpl();
        switch(action){
            case "get_accident":
                get_accident(response,ac);
                break;
            case "get_listId":
                get_listId(response,ac);
                break;
            case "add_accident":
                add_accident(request,response,ac);
                break;
            case "change_accident":
                change_accident(request,response,ac);
                break;
            case "delete_accident":
                delete_accident(request,response,ac);
                break;
            case "search_accident":
                search_accident(request,response,ac);
                break;
        }
    }

    private void get_listId(HttpServletResponse response, accidentDAOlmpl ac) throws IOException{
        operateDAOlmpl op = new operateDAOlmpl();
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("car_id_list",op.getListId("select car_id from car","car_id"));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    private void get_accident(HttpServletResponse response, accidentDAOlmpl ac) throws IOException{
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("accident",ac.getAccident("select * from accident"));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    private void add_accident(HttpServletRequest request,HttpServletResponse response, accidentDAOlmpl ac) throws IOException{
        PrintWriter out = response.getWriter();
        int acid = Integer.parseInt(request.getParameter("acid"));
        String actime = request.getParameter("actime");
        String acaddress = request.getParameter("acaddress");
        String acreason = request.getParameter("acreason");
        int acdie = Integer.parseInt(request.getParameter("acdie"));
        out.println(ac.addAccident(acid,actime,acaddress,acreason,acdie));
        out.flush();
        out.close();
    }

    private void change_accident(HttpServletRequest request,HttpServletResponse response, accidentDAOlmpl ac) throws IOException{
        PrintWriter out = response.getWriter();
        int acid = Integer.parseInt(request.getParameter("acid"));
        String actime = request.getParameter("actime");
        String acaddress = request.getParameter("acaddress");
        String acreason = request.getParameter("acreason");
        int acdie = Integer.parseInt(request.getParameter("acdie"));
        int aid = Integer.parseInt(request.getParameter("aid"));
        out.println(ac.changeAccident(acid,actime,acaddress,acreason,acdie,aid));
        out.flush();
        out.close();
    }

    private void delete_accident(HttpServletRequest request,HttpServletResponse response, accidentDAOlmpl ac) throws IOException{
        PrintWriter out = response.getWriter();
        int aid = Integer.parseInt(request.getParameter("aid"));
        out.println(ac.deleteAccident(aid));
        out.flush();
        out.close();
    }

    private void search_accident(HttpServletRequest request,HttpServletResponse response, accidentDAOlmpl ac) throws IOException{
        int acid = Integer.parseInt(request.getParameter("acid"));
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("accident",ac.searchAccident(acid));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
