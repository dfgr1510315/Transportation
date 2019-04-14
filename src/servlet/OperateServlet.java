package servlet;

import daoImpl.operateDAOlmpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OperateServlet")
public class OperateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        operateDAOlmpl op = new operateDAOlmpl();
        switch(action){
            case "get_operate":
                get_operate(response,op);
                break;
            case "get_listId":
                get_listId(response,op);
                break;
            case "add_operate":
                add_operate(request,response,op);
                break;
            case "change_operate":
                change_operate(request,response,op);
                break;
            case "delete_operate":
                delete_operate(request,response,op);
                break;
            case "search_operate":
                search_operate(request,response,op);
                break;
        }
    }

    private void get_listId(HttpServletResponse response,operateDAOlmpl op)throws IOException{
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("car_id_list",op.getListId("select car_id from car","car_id"));
        jsonObj.put("ln_id_list",op.getListId("select License_number from driver","License_number"));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    private void get_operate(HttpServletResponse response, operateDAOlmpl op)throws IOException{
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("op_list",op.getOperate("select * from operate"));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    private void add_operate(HttpServletRequest request,HttpServletResponse response, operateDAOlmpl op) throws IOException{
        PrintWriter out = response.getWriter();
        String License_number = request.getParameter("License_number");
        int car_id = Integer.parseInt(request.getParameter("car_id"));
        String goods = request.getParameter("goods");
        String destination = request.getParameter("destination");
        String stime = request.getParameter("stime");
        String rtime = request.getParameter("rtime");
        float driving_time = Float.parseFloat(request.getParameter("driving_time"));
        out.println(op.addOperate(License_number,car_id,goods,destination,stime,rtime,driving_time));
        out.flush();
        out.close();
    }

    private void change_operate(HttpServletRequest request,HttpServletResponse response, operateDAOlmpl op)throws IOException {
        PrintWriter out = response.getWriter();
        int op_id = Integer.parseInt(request.getParameter("op_id"));
        String License_number = request.getParameter("License_number");
        int car_id = Integer.parseInt(request.getParameter("car_id"));
        String goods = request.getParameter("goods");
        String destination = request.getParameter("destination");
        String stime = request.getParameter("stime");
        String rtime = request.getParameter("rtime");
        float driving_time = Float.parseFloat(request.getParameter("driving_time"));
        out.println(op.changeOperate(op_id,License_number,car_id,goods,destination,stime,rtime,driving_time));
        out.flush();
        out.close();
    }

    private void delete_operate(HttpServletRequest request,HttpServletResponse response, operateDAOlmpl op)throws IOException {
        PrintWriter out = response.getWriter();
        int op_id = Integer.parseInt(request.getParameter("op_id"));
        out.println(op.deleteOperate(op_id));
        out.flush();
        out.close();
    }

    private void search_operate(HttpServletRequest request,HttpServletResponse response, operateDAOlmpl op)throws IOException {
        int car_id = Integer.parseInt(request.getParameter("car_id"));
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("car_list",op.searchOperate(car_id));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
