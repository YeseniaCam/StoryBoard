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
public class ListAudios {
        String ruta;
		List<Audio> audios = new ArrayList<>();
		Element audioXML;
		SAXBuilder builder;
		File xmlFile;
		Document document;
		int indiceAudio;
		Element rootNode;
		List list;

    public ListAudios(String ruta){
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


	public List<Audio> generateList() {

		for (int i = 0; i < list.size(); i++) {

           Element node = (Element) list.get(i);    
           if (node.getChildText("Audio") != null){
                audios.add(new Audio(node.getChildText("Nombre"),node.getChildText("Audio"),node.getChildText("Tipo")));
           }
		}
    return audios;
	}
	public Audio getOneAudio(String texto){

		Audio audiotosend = new Audio();
		for (int i = 0; i < list.size(); i++) {
			 Element node = (Element) list.get(i);   
			 String name =  node.getChildText("Nombre");
			 if (name.equals(texto)) {
                audiotosend.name = name;
                audiotosend.tipo = node.getChildText("Tipo");
                audiotosend.audio = node.getChildText("Audio");
				audioXML = node;
				indiceAudio = i;
				return audiotosend;
			 }	
		}

		return audiotosend;
	}

	public void deleteAudio(){
		list.remove(indiceAudio);
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
