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
public class ListVideos {
    String ruta;
		List<Video> videos = new ArrayList<>();
		Element videoXML;
		SAXBuilder builder;
		File xmlFile;
		Document document;
		int indiceVideo;
		Element rootNode;
		List list;

    public ListVideos(String ruta){
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


	public List<Video> generateList() {

		for (int i = 0; i < list.size(); i++) {

           Element node = (Element) list.get(i);    
           if (node.getChildText("Video") != null){
                videos.add(new Video(node.getChildText("Nombre"),node.getChildText("Video"),node.getChildText("Tipo")));
           }
		}
    return videos;
	}
	public Video getOneVideo(String video){

		Video vidtosend = new Video();
		for (int i = 0; i < list.size(); i++) {
			 Element node = (Element) list.get(i);   
			 String name =  node.getChildText("Nombre");
			 if (name.equals(video)) {
                    vidtosend.name = name;
                    vidtosend.tipo = node.getChildText("Tipo");
                    vidtosend.video = node.getChildText("Video");
					videoXML = node;
					indiceVideo = i;
					return vidtosend;
			 }	
		}

		return vidtosend;
	}
	public void updateVideo(Element node,String Nombre,String Video, String Tipo){
		node.getChild("Nombre").setText(Nombre);
		node.getChild("Video").setText(Video);
		node.getChild("Tipo").setText(Tipo);
		save();
	}
	public void deleteVideo(){
		list.remove(indiceVideo);
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
