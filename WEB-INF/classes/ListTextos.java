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
public class ListTextos {
        String ruta;
		List<Texto> textos = new ArrayList<>();
		Element textoXML;
		SAXBuilder builder;
		File xmlFile;
		Document document;
		int indiceTexto;
		Element rootNode;
		List list;

    public ListTextos(String ruta){
				this.ruta = ruta;
				try{
					builder = new SAXBuilder();
					xmlFile = new File(ruta);
					document = builder.build(xmlFile);
					rootNode = document.getRootElement();
					list = rootNode.getChildren("StoryBoard");
					}catch(Exception e){
						e.printStackTrace();
					}
		}


	public List<Texto> generateList() {

		for (int i = 0; i < list.size(); i++) {

           Element node = (Element) list.get(i);    
           if (node.getChildText("Texto") != null){
                textos.add(new Texto(node.getChildText("Nombre"),node.getChildText("Texto"),node.getChildText("Tipo")));
           }
		}
    return textos;
	}
	public Texto getOneText(String texto){

		Texto textosend = new Texto();
		for (int i = 0; i < list.size(); i++) {
			 Element node = (Element) list.get(i);   
			 String name =  node.getChildText("Nombre");
			 if (name.equals(texto)) {
                    textosend.name = name;
                    textosend.tipo = node.getChildText("Tipo");
                    textosend.texto = node.getChildText("Texto");
					textoXML = node;
					indiceTexto = i;
					return textosend;
			 }	
		}

		return textosend;
	}
	public void updateTexto(Element node,String Texto){
		node.getChild("Texto").setText(Texto);
		save();
	}
	public void deleteTexto(){
		list.remove(indiceTexto);
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
