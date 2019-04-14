package servlet;

import daoImpl.carDAOlmpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CarServlet.do")
public class CarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        carDAOlmpl cl = new carDAOlmpl();
        switch(action){
            case "get_car":
                get_car(response,cl);
                break;
            case "add_car":
                add_car(request,response,cl);
                break;
            case "change_car":
                change_car(request,response,cl);
                break;
            case "delete_car":
                delete_car(request,response,cl);
                break;
            case "search_car":
                search_car(request,response,cl);
                break;
        }
    }

    private void get_car( HttpServletResponse response,carDAOlmpl cl) throws IOException{
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("car_list",cl.getCar("select * from car order by car_id"));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    private void add_car(HttpServletRequest request,HttpServletResponse response,carDAOlmpl cl)throws IOException{
        PrintWriter out = response.getWriter();
        String car_type = request.getParameter("car_type");
        String color = request.getParameter("color");
        int max_guest = Integer.parseInt(request.getParameter("max_guest"));
        out.println(cl.addCar(car_type,color,max_guest));
        out.flush();
        out.close();
    }

    private void change_car(HttpServletRequest request,HttpServletResponse response,carDAOlmpl cl)throws IOException{
        PrintWriter out = response.getWriter();
        int car_id = Integer.parseInt(request.getParameter("car_id"));
        String car_type = request.getParameter("car_type");
        String color = request.getParameter("color");
        int max_guest = Integer.parseInt(request.getParameter("max_guest"));
        out.println(cl.changeCar(car_id,car_type,color,max_guest));
        out.flush();
        out.close();
    }

    private void delete_car(HttpServletRequest request,HttpServletResponse response,carDAOlmpl cl)throws IOException{
        PrintWriter out = response.getWriter();
        int car_id = Integer.parseInt(request.getParameter("car_id"));
        out.println(cl.deleteCar(car_id));
        out.flush();
        out.close();
    }

    private void search_car(HttpServletRequest request,HttpServletResponse response,carDAOlmpl cl)throws IOException{
        int car_id = Integer.parseInt(request.getParameter("car_id"));
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObj.put("car_list",cl.searchCar(car_id));
        out.println(jsonObj);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
