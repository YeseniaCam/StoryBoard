import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Iterable;
import java.util.List;
import java.io.File;


public class DeleteText extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String user = (String)session.getAttribute("user");
        String texto = request.getParameter("texto");
        String ruta = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
        ListTextos textos = new ListTextos(ruta);
        Texto t = textos.getOneText(texto);
        textos.deleteTexto();
        response.sendRedirect("MainStoryBoards");
    }
}
