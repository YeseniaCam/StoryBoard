import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Iterable;
import java.util.List;
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

public class SendGrades extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();       
         HttpSession sesion = request.getSession();
        String user =request.getParameter("alumno");
        String rutaf = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/AlumnoStory.xml");
        String calificacion = request.getParameter("calificacion");
        String story = request.getParameter("story");

        try{
            SAXBuilder builder = new SAXBuilder();

            File documentJDOM = new File(rutaf);
            Document doc = builder.build(documentJDOM);
            Element rootNode = doc.getRootElement();
            List list = rootNode.getChildren("AlumnoStory");
            XMLOutputter xmlOut = new XMLOutputter();

            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);
                if (node.getChildText("storyboard").equals(story))
                    node.getChild("calificacion").setText(calificacion);
                xmlOut.output(doc, new FileWriter(rutaf));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
