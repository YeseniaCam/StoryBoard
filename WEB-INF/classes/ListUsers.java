import java.io.IOException;
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
import java.util.List;
import java.util.ArrayList;

public class ListUsers {
    String ruta;
		List<User> users = new ArrayList<>();
		Element userXML;
		SAXBuilder builder;
		File xmlFile;
		Document document;
		int indiceUser;
		Element rootNode;
		List list;

    public ListUsers(String ruta){
				this.ruta = ruta;
				try{
					builder = new SAXBuilder();
					xmlFile = new File(ruta);
					document = builder.build(xmlFile);
					rootNode = document.getRootElement();
					list = rootNode.getChildren("Usuario");
					}catch(Exception e){
						e.printStackTrace();
					}
		}


	public List<User> generateList() {

		for (int i = 0; i < list.size(); i++) {

		   Element node = (Element) list.get(i);    
           users.add(new User(node.getChildText("Username"),node.getChildText("Correo"),node.getChildText("Tipo")));
		}
    return users;
	}
	public User getOneUser(String user){

		User usertosend = new User();
		for (int i = 0; i < list.size(); i++) {
			 Element node = (Element) list.get(i);   
			 String username =  node.getChildText("Username");
			 if (username.equals(user)) {
					 usertosend.username = username;
					 usertosend.name = node.getChildText("Nombre");
					 usertosend.correo = node.getChildText("Correo");
					 usertosend.password = node.getChildText("Password");
					 usertosend.tipo = node.getChildText("Tipo");
					 userXML = node;
					 indiceUser = i;
					 return usertosend;
			 }	
		}

		return usertosend;
	}
	public void updateUser(Element node,String Nombre,String Correo, String Username, String Password, String Tipo){
		node.getChild("Nombre").setText(Nombre);
		node.getChild("Correo").setText(Correo);
		node.getChild("Username").setText(Username);
		node.getChild("Password").setText(Password);
		node.getChild("Tipo").setText(Tipo);
		save();
	}
	public void deleteUser(){
		list.remove(indiceUser);
		save();
	}
	public void save(){
		try {
			XMLOutputter xmlout = new XMLOutputter();
			FileOutputStream fileout = new FileOutputStream(ruta);
			xmlout.output(document, fileout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
