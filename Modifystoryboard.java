import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

public class Modifystoryboard  {

    public void get()  {
        
        String rutaf = "Usuarios/Profesor/base.xml";
        ListStoryboards ls = new ListStoryboards(rutaf);
        CStoryBoard story =  ls.getOneStory("Story1");
        
        for (int i = 0; i < story.slides.size(); i++) {
            System.out.println(story.slides.get(i));
        }     
             
    }
    public static void main(String[] args) {
        Modifystoryboard m = new Modifystoryboard();
        m.get();
    }
}