
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

public class UpdateStoryBoard extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		String ruta = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
        String slides = request.getParameter("slides");
        String nombre = request.getParameter("modificable");
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
        ListStoryboards ls = new ListStoryboards(ruta);
        CStoryBoard story =  ls.getOneStory(nombre);
        ls.updateStory(ls.storyXML,slidestosave);
        response.sendRedirect("./MainStoryBoards");

	}
}
