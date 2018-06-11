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


public class XMLvideo extends Thread{
	String nombre;
	String video;
	String type;
	String ruta;
	
	public XMLvideo(String ruta, String nombre,String video,String type){
		this.nombre=nombre;
		this.video = video;
		this.type = type;
		this.ruta = ruta;
	}
    public void run() {
		try{
			SAXBuilder builder = new SAXBuilder();

			File documentJDOM = new File(ruta);
			Document doc = builder.build(documentJDOM);
			Element raiz = doc.getRootElement();  
			Element element = new Element("StoryBoard");
			Element name = new Element("Nombre");
			name.setText(nombre);
			element.addContent(name);
			Element pass = new Element("Video");
			pass.setText(video);
			element.addContent(pass);
			Element tipo = new Element("Tipo");
			tipo.setText(type);
			element.addContent(tipo);
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
}
