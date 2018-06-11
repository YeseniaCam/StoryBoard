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

public class GroupActions extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("tipo");
		String ruta = request.getSession().getServletContext().getRealPath("/xml/grupos.xml");
		String rutaus = request.getSession().getServletContext().getRealPath("/xml/Usuarios.xml");
		if (type.equals("0")) {
			String name=request.getParameter("grupo");
			String prof=request.getParameter("profesor");
			addGroup(name,prof,ruta);
		}else if(type.equals("1")){
			String name=request.getParameter("grupo");
			String prof=request.getParameter("profesor");
			updateGroup(name,prof,ruta);
		}else if(type.equals("2")){
			String name=request.getParameter("grupo");
			deleteGroup(name,ruta);
		}else if(type.equals("3")){
			String name=request.getParameter("grupo");
			String alumno=request.getParameter("alumno");
			String asignado=request.getParameter("asignado");
			assignAlumn(name,alumno,asignado,rutaus);
		}


	}


	public void addGroup(String name, String profesor, String ruta){
		try{
		SAXBuilder builder = new SAXBuilder();

			File documentJDOM = new File(ruta);
			Document doc = builder.build(documentJDOM);

			Element raiz = doc.getRootElement();  
			Element element = new Element("Grupo");

			Element nameel = new Element("Nombre");
			nameel.setText(name);
			element.addContent(nameel);

			Element profesorel = new Element("Profesor");
			profesorel.setText(profesor);
			element.addContent(profesorel);
			raiz.addContent(element);

			XMLOutputter xmlout = new XMLOutputter();
			FileOutputStream fileout = new FileOutputStream(ruta);
			xmlout.output(doc, fileout);
		}
		catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	public void updateGroup(String name, String profesor, String ruta){
		try{
            SAXBuilder builder = new SAXBuilder();

            File documentJDOM = new File(ruta);
            Document doc = builder.build(documentJDOM);
            Element rootNode = doc.getRootElement();
            List list = rootNode.getChildren("Grupo");
            XMLOutputter xmlOut = new XMLOutputter();

            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);
                if (node.getChildText("Nombre").equals(name))
                    node.getChild("Profesor").setText(profesor);
                xmlOut.output(doc, new FileWriter(ruta));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

	}


	public void deleteGroup(String name, String ruta){
		try{
            SAXBuilder builder = new SAXBuilder();

            File documentJDOM = new File(ruta);
            Document doc = builder.build(documentJDOM);
            Element rootNode = doc.getRootElement();
            List list = rootNode.getChildren("Grupo");
            XMLOutputter xmlOut = new XMLOutputter();

            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);
                if (node.getChildText("Nombre").equals(name))
                    list.remove(i);
                xmlOut.output(doc, new FileWriter(ruta));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	public void assignAlumn(String grupo, String alumno, String asignado, String ruta){
		try{
            SAXBuilder builder = new SAXBuilder();

            File documentJDOM = new File(ruta);
            Document doc = builder.build(documentJDOM);
            Element rootNode = doc.getRootElement();
            List list = rootNode.getChildren("Usuario");
            XMLOutputter xmlOut = new XMLOutputter();

            for (int i = 0; i < list.size(); i++) {
            	String asig=(asignado.equals("1")?grupo:"");
                Element node = (Element) list.get(i);
                if (node.getChildText("Nombre").equals(alumno) && node.getChildText("Tipo").equals("Estudiante"))
                    node.getChild("Grupo").setText(asig);

                xmlOut.output(doc, new FileWriter(ruta));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

	}


}
