import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import java.util.List;
import org.jdom.output.XMLOutputter;
import java.io.PrintWriter;
import java.io.FileWriter;
import org.jdom.input.SAXBuilder;
import org.jdom.input.DOMBuilder;
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.OutputStream;  
import java.io.InputStream;  
import java.io.File;
import javax.servlet.http.HttpSession;

public class UpdateXMLImagen extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		String tipo = (String)session.getAttribute("tipo");
		String nombre = (String)session.getAttribute("nombre_story");
		String ruta = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
		String imagen = request.getParameter("ruta");
		XMLimagen vid = new XMLimagen(ruta, nombre, imagen, tipo);
		try {
			vid.start();
			vid.join();
			 //////////////////
		        String rutaA=request.getSession().getServletContext().getRealPath("/");
		        XMLAssignStoryBoard toAlumns=new XMLAssignStoryBoard(nombre,rutaA,user,tipo);
		        toAlumns.start();
		        toAlumns.join();
		        //////////////////
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("./MainStoryBoards");
    }
}