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

public class ListGroups {
    String ruta;
		List<Group> group = new ArrayList<>();
		Element userXML;
		SAXBuilder builder;
		File xmlFile;
		Document document;
		int indiceUser;
		Element rootNode;
		List list;

    public ListGroups(String ruta){
				this.ruta = ruta;
				try{
					builder = new SAXBuilder();
					xmlFile = new File(ruta);
					document = builder.build(xmlFile);
					rootNode = document.getRootElement();
					list = rootNode.getChildren("Grupo");
					}catch(Exception e){
						e.printStackTrace();
					}
		}


	public List<Group> generateList() {

		for (int i = 0; i < list.size(); i++) {

		   Element node = (Element) list.get(i);    
           group.add(new Group(node.getChildText("Nombre"),node.getChildText("Profesor")));
		}
    return group;
	}

}
