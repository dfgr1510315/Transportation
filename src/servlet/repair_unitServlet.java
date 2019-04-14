package servlet;

import daoImpl.repair_unitDAOlmpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "repair_unitServlet")
public class repair_unitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        repair_unitDAOlmpl rul = new repair_unitDAOlmpl();
        switch(action){
            case "get_repair_unit":
                get_repair_unit(response,rul);
                break;
            case "add_repair_unit":
                add_repair_unit(request,response,rul);
                break;
            case "change_repair_unit":
                change_repair_unit(request,response,rul);
                break;
            case "delete_repair_unit":
                delete_repair_unit(request,response,rul);
                break;
            case "search_repair_unit":
                search_repair_unit(request,response,rul);
                break;
        }
    }

    private void get_repair_unit(HttpServletResponse response, repair_unitDAOlmpl rul) throws IOException{
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("repair_unit",rul.getRepair_unit("select * from repair_unit"));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    private void add_repair_unit(HttpServletRequest request,HttpServletResponse response, repair_unitDAOlmpl rul) throws IOException{
        PrintWriter out = response.getWriter();
        String reuname = request.getParameter("reuname");
        String reutel = request.getParameter("reutel");
        String reuaddress = request.getParameter("reuaddress");
        out.println(rul.addRepair_unit(reuname,reutel,reuaddress));
        out.flush();
        out.close();
    }

    private void change_repair_unit(HttpServletRequest request,HttpServletResponse response, repair_unitDAOlmpl rul) throws IOException{
        PrintWriter out = response.getWriter();
        String reuname = request.getParameter("reuname");
        String reutel = request.getParameter("reutel");
        String reuaddress = request.getParameter("reuaddress");
        String oldname = request.getParameter("oldname");
        out.println(rul.changeRepair_unit(reuname,reutel,reuaddress,oldname));
        out.flush();
        out.close();
    }

    private void delete_repair_unit(HttpServletRequest request,HttpServletResponse response, repair_unitDAOlmpl rul) throws IOException{
        PrintWriter out = response.getWriter();
        String reuname = request.getParameter("reuname");
        out.println(rul.deleteRepair_unit(reuname));
        out.flush();
        out.close();
    }

    private void search_repair_unit(HttpServletRequest request,HttpServletResponse response, repair_unitDAOlmpl rul) throws IOException{
        String reuname = request.getParameter("reuname");
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("repair_unit",rul.searchRepair_unit(reuname));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
