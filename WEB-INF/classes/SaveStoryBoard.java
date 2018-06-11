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

public class SaveStoryBoard extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		String tipo = (String)session.getAttribute("tipo");
		String nombre = (String)session.getAttribute("nombre_story");
		String ruta = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
        String slides = request.getParameter("slides");
        StringTokenizer tokens=new StringTokenizer(slides, "?");
        List<String> slidestosave = new ArrayList();
        int i = 0;
        while(tokens.hasMoreTokens()){
            String cadena = tokens.nextToken();
            if (i!=0){
                cadena = cadena.substring(1);
            }
            slidestosave.add(cadena);
            i++;
        }
        
        XMLStoryBoard s = new XMLStoryBoard(ruta, nombre, slidestosave, tipo);
        s.start();
        //////////////////
        String rutaA=request.getSession().getServletContext().getRealPath("/");
        XMLAssignStoryBoard toAlumns=new XMLAssignStoryBoard(nombre,rutaA,user,tipo);
        toAlumns.start();
        //////////////////
        response.sendRedirect("./MainStoryBoards");

	}
}
