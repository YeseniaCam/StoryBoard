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

public class Registro extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		request.setCharacterEncoding("UTF-8");
		String ruta = request.getSession().getServletContext().getRealPath("/xml/Usuarios.xml");
		String ruta2 = request.getSession().getServletContext().getRealPath("/Usuarios");
		XML handler = new XML(ruta,request.getParameter("nombre"),request.getParameter("correo"),request.getParameter("user"),request.getParameter("contrasena1"),request.getParameter("tipo"));
		handler.start();
		new File(ruta2 +"/"+request.getParameter("user")).mkdir();
		new File(ruta2 +"/"+request.getParameter("user")+"/videos").mkdir();
		new File(ruta2 +"/"+request.getParameter("user")+"/imagenes").mkdir();
		new File(ruta2 +"/"+request.getParameter("user")+"/imagenes/Misimagenes").mkdir();
		new File(ruta2 +"/"+request.getParameter("user")+"/imagenes/Misimagenes/png").mkdir();
		new File(ruta2 +"/"+request.getParameter("user")+"/audios").mkdir();
		File source = new File(ruta2 + "/base.xml");
		File dest = new File(ruta2 +"/"+request.getParameter("user") +"/base.xml");
		///////////////////////////////
		File source2= new File("/");
		File dest2= new File("/");
		if (request.getParameter("tipo").equals("Estudiante")) {
		 source2 = new File(ruta2 + "/AlumnoStory.xml");
		 dest2 = new File(ruta2 +"/"+request.getParameter("user") +"/AlumnoStory.xml");	
		}
		
		/////////////////////////////
		try {
			copyFile(source, dest);
			///
			if (request.getParameter("tipo").equals("Estudiante")) {
			copyFile(source2,dest2);
				
			}
			///
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("./AltasBajasCambios");
	}

	private void copyFile(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}
}
