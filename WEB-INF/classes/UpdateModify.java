import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Iterable;
import java.util.List;


public class UpdateModify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String ruta = request.getSession().getServletContext().getRealPath("/xml/Usuarios.xml");
        ListUsers users = new ListUsers(ruta);
        String userString = request.getParameter("seconduser");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena1");
        String tipo = request.getParameter("tipo");
        User usuario = users.getOneUser(request.getParameter("usuario"));
        users.updateUser(users.userXML,nombre,correo,userString,contrasena,tipo);
        response.sendRedirect("AltasBajasCambios");


    }
}
