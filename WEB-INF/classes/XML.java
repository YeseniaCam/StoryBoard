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


public class XML extends Thread{
	String nombre;
	String correo;
	String usuario;
	String contrasena;
	String type;
	String ruta;
	
	public XML(String ruta, String nombre,String correo,String usuario,String contrasena,String type){
		this.nombre=nombre;
		this.correo=correo;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.type = type;
		this.ruta = ruta;
	}
    public void run() {
		try{
			SAXBuilder builder = new SAXBuilder();

			File documentJDOM = new File(ruta);
			Document doc = builder.build(documentJDOM);
			Element raiz = doc.getRootElement();  
			Element element = new Element("Usuario");
			Element name = new Element("Nombre");
			name.setText(nombre);
			element.addContent(name);
			Element email = new Element("Correo");
			email.setText(correo);
			element.addContent(email);
			Element user = new Element("Username");
			user.setText(usuario);
			element.addContent(user);
			Element pass = new Element("Password");
			pass.setText(contrasena);
			element.addContent(pass);
			Element tipo = new Element("Tipo");
			tipo.setText(type);
			element.addContent(tipo);
			//////////
			if (type.equals("Estudiante")) {
				Element grupo = new Element("Grupo");
				grupo.setText(type);
				element.addContent(grupo);
			}
			//////////
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
