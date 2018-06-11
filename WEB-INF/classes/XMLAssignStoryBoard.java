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
import java.io.File;
import java.util.ArrayList;

public class XMLAssignStoryBoard extends Thread{
	String nombre;
	String ruta;
	String user;
	String tipo;
	public XMLAssignStoryBoard(String nombre, String ruta, String user,String tipo){
		this.nombre=nombre;
		this.ruta = ruta;
		this.user = user;
		this.tipo=tipo;
	}
    public void run() {
		try{

			List<String> groups = getUserGroups(user);
			for (int i = 0; i < groups.size(); i++) {
				System.out.println("entra al primer array con "+groups.get(i));
				List<String> alumns =getAlumnsByGroup(groups.get(i));
				for (int j = 0; j < alumns.size(); j++) {
					System.out.println("entra al segundo array con "+alumns.get(j));
					String ruta = (this.ruta+"/Usuarios/"+alumns.get(j)+"/AlumnoStory.xml");
					AssignStoryBoard(ruta);
				}
			}

		

			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	public List<String> getUserGroups(String user){
		List<String> groups= new ArrayList<String>();
		String ruta = (this.ruta+"/xml/grupos.xml");
		try{
            SAXBuilder builder = new SAXBuilder();

            File documentJDOM = new File(ruta);
            Document doc = builder.build(documentJDOM);
            Element rootNode = doc.getRootElement();
            List list = rootNode.getChildren("Grupo");
            XMLOutputter xmlOut = new XMLOutputter();

            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);
                if (node.getChildText("Profesor").equals(user))
                   groups.add(node.getChild("Nombre").getText());

                xmlOut.output(doc, new FileWriter(ruta));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return groups;
	}

	public List<String> getAlumnsByGroup(String group){
		List<String> alumnos= new ArrayList<String>();
		String ruta = (this.ruta+"/xml/Usuarios.xml");
		try{
            SAXBuilder builder = new SAXBuilder();

            File documentJDOM = new File(ruta);
            Document doc = builder.build(documentJDOM);
            Element rootNode = doc.getRootElement();
            List list = rootNode.getChildren("Usuario");
            XMLOutputter xmlOut = new XMLOutputter();

            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);
                if (node.getChild("Grupo")==null) {
                	
                }else{
                	 if (node.getChildText("Grupo").equals(group) && node.getChildText("Tipo").equals("Estudiante")){
	                   alumnos.add(node.getChild("Username").getText());
	                  System.out.println(node.getChild("Username").getText());
	                }

	                xmlOut.output(doc, new FileWriter(ruta));
                }
               
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return alumnos;
	}



	public void AssignStoryBoard(String ruta){
		try{
			 SAXBuilder builder = new SAXBuilder();

            File documentJDOM = new File(ruta);
			Document doc = builder.build(documentJDOM);
			Element raiz = doc.getRootElement();  
			Element element = new Element("AlumnoStory");
			Element storyboard = new Element("storyboard");
			storyboard.setText(this.nombre);
            element.addContent(storyboard);    
			Element mensaje = new Element("mensaje");
			mensaje.setText("");
            element.addContent(mensaje);
            Element tipo = new Element("tipo");
			tipo.setText(this.tipo);
            element.addContent(tipo);
            Element calificacion = new Element("calificacion");
			calificacion.setText("");
            element.addContent(calificacion);
            Element profesor = new Element("profesor");
			profesor.setText(this.user);
            element.addContent(profesor);

			raiz.addContent(element);
			XMLOutputter xmlout = new XMLOutputter();
			FileOutputStream fileout = new FileOutputStream(ruta);
			
			xmlout.output(doc, fileout);
			fileout.close();
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
		}
		
        
	}
}
