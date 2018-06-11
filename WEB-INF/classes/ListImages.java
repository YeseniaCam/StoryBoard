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
public class ListImages {
        String ruta;
		List<Imagen> imagenes = new ArrayList<>();
		Element imagenXML;
		SAXBuilder builder;
		File xmlFile;
		Document document;
		int indiceImagen;
		Element rootNode;
		List list;

    public ListImages(String ruta){
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


	public List<Imagen> generateList() {

		for (int i = 0; i < list.size(); i++) {

           Element node = (Element) list.get(i);    
           if (node.getChildText("Imagen") != null){
                imagenes.add(new Imagen(node.getChildText("Nombre"),node.getChildText("Imagen"),node.getChildText("Tipo")));
           }
		}
    return imagenes;
	}
	public Imagen getOneImage(String texto){

		Imagen imatosend = new Imagen();
		for (int i = 0; i < list.size(); i++) {
			 Element node = (Element) list.get(i);   
			 String name =  node.getChildText("Nombre");
			 if (name.equals(texto)) {
                imatosend.name = name;
                imatosend.tipo = node.getChildText("Tipo");
                imatosend.imagen = node.getChildText("Imagen");
				imagenXML = node;
				indiceImagen = i;
				return imatosend;
			 }	
		}

		return imatosend;
	}

	public void deleteImage(){
		list.remove(indiceImagen);
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
