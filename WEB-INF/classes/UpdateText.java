
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateText extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		String ruta = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
        String texto = request.getParameter("texto");
        String texttoshow = request.getParameter("texttoshow");
        ListTextos lt = new ListTextos(ruta);
        Texto t = lt.getOneText(texto);
        lt.updateTexto(lt.textoXML, texttoshow);
        response.sendRedirect("./MainStoryBoards?texto="+texttoshow);

	}
}
