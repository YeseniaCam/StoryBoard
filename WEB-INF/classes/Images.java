
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Images {
    String ruta;
    String secondruta;
    List<Listas> imagenes;
    public Images(String ruta,String secondruta){
        this.ruta = ruta;
        this.secondruta = secondruta;
        imagenes = new ArrayList<>();
    }
    public List<Listas> generateLists(){
        File dir = new File(ruta);
        File[] filesList = dir.listFiles();
        if (filesList != null) {
            for (File file : filesList) {
                if (file.isDirectory()) {
                    File lastdir = new File(ruta +"/"+file.getName() + "/png");
                    List<String> PNG = new ArrayList<>();
                    for (File ffiles : lastdir.listFiles()) {
                        String ruta1 = ffiles.getName();
                        PNG.add(secondruta +"/"+file.getName() + "/png/" + ruta1);
                    }
                    Listas l = new Listas(file.getName(), PNG);
                    imagenes.add(l);
                }        
            }
        }
        return imagenes;
    }
}