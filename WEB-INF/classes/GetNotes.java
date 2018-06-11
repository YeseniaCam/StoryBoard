import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import java.util.ArrayList;

public class GetNotes extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/xml;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession();
        String user =(String)sesion.getAttribute("user");
        String nombre = request.getParameter("name");
        String ruta = request.getSession().getServletContext().getRealPath("/Usuarios");
        String rutai = request.getSession().getServletContext().getRealPath("/");
        try{

            Element root = new Element("Notas");
            Document doc = new Document(root);
            doc.setRootElement(root);

            List<String> groups = getUserGroups(user,rutai);
              for (int i = 0; i < groups.size(); i++) {
                List<String> alumns =getAlumnsByGroup(groups.get(i),rutai);
                for (int j = 0; j < alumns.size(); j++) {
                  String ruta1 = (rutai+"/Usuarios/"+alumns.get(j)+"/AlumnoStory.xml");

                  Element nota = new Element("Nota");
                  nota.addContent(new Element("Mensaje").setText(getMessagesFrom(ruta1,nombre,user)));
                  nota.addContent(new Element("Calificacion").setText(getGradeFrom(ruta1,nombre,user)));
                  nota.addContent(new Element("Alumno").setText(alumns.get(j)));
                  doc.getRootElement().addContent(nota);    

                }
              }

            XMLOutputter xmOut = new XMLOutputter(); 
            out.println(xmOut.outputString(doc));
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public List<String> getUserGroups(String user,String rutai){
      List<String> groups= new ArrayList<String>();
      String ruta = (rutai+"/xml/grupos.xml");
      try{
              SAXBuilder builder = new SAXBuilder();

              File documentJDOM = new File(ruta);
              Document doc = builder.build(documentJDOM);
              Element rootNode = doc.getRootElement();
              List list = rootNode.getChildren("Grupo");
              XMLOutputter xmlOut = new XMLOutputter();

              for (int i = 0; i < list.size(); i++) {

                  Element node = (Element) list.get(i);
                  if (node.getChildText("Profesor").equals(user))
                     groups.add(node.getChild("Nombre").getText());

                  xmlOut.output(doc, new FileWriter(ruta));
              }
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          return groups;
    }

    public List<String> getAlumnsByGroup(String group,String rutai){
      List<String> alumnos= new ArrayList<String>();
      String ruta = (rutai+"/xml/Usuarios.xml");
      try{
              SAXBuilder builder = new SAXBuilder();

              File documentJDOM = new File(ruta);
              Document doc = builder.build(documentJDOM);
              Element rootNode = doc.getRootElement();
              List list = rootNode.getChildren("Usuario");
              XMLOutputter xmlOut = new XMLOutputter();

              for (int i = 0; i < list.size(); i++) {

                  Element node = (Element) list.get(i);
                  if (node.getChild("Grupo")==null) {
                    
                  }else{
                     if (node.getChildText("Grupo").equals(group) && node.getChildText("Tipo").equals("Estudiante")){
                       alumnos.add(node.getChild("Username").getText());
                      System.out.println(node.getChild("Username").getText());
                    }

                    xmlOut.output(doc, new FileWriter(ruta));
                  }
                 
              }
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          return alumnos;
    }






    public String getMessagesFrom(String ruta ,String nombre,String user){
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta);

      try {

        Document document = (Document) builder.build(xmlFile);
        Element rootNode = document.getRootElement();
        List list = rootNode.getChildren("AlumnoStory");

        for (int i = 0; i < list.size(); i++) {

           Element node = (Element) list.get(i);

           if (node.getChildText("storyboard").equals(nombre)&&node.getChildText("profesor").equals(user)) {
               return node.getChildText("mensaje");
           }

        }

      } catch (IOException io) {
        System.out.println(io.getMessage());
      } catch (JDOMException jdomex) {
        System.out.println(jdomex.getMessage());
      }
      return "";
    }

    public String getGradeFrom(String ruta, String nombre,String user){
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta);

      try {

        Document document = (Document) builder.build(xmlFile);
        Element rootNode = document.getRootElement();
        List list = rootNode.getChildren("AlumnoStory");

        for (int i = 0; i < list.size(); i++) {

           Element node = (Element) list.get(i);

           if (node.getChildText("storyboard").equals(nombre)&&node.getChildText("profesor").equals(user)) {
               return node.getChildText("calificacion");
           }

        }

      } catch (IOException io) {
        System.out.println(io.getMessage());
      } catch (JDOMException jdomex) {
        System.out.println(jdomex.getMessage());
      }
      return "";
    }

}
