import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("user");
        String type = request.getParameter("type");
        if(type.equals("Administrador")){
            response.sendRedirect("Administrador?user="+user);
        }
        else if(type.equals("Estudiante")){
            response.sendRedirect("Estudiante?user="+user);
        }  
        else{
            response.sendRedirect("Profesor?user="+user);
        }
    }
}
