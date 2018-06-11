import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Iterable;
import java.util.List;


public class Groups extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("user");
        String type = request.getParameter("type");
        String ruta = request.getSession().getServletContext().getRealPath("/xml/grupos.xml");
        ListGroups listu = new ListGroups(ruta);
        List<Group> l = listu.generateList();     
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es' class='h-100'>");
        out.println("<head>");
        out.println("<title>Administrador</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.1.0/fabric.all.min.js' ></script>");
        out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
        out.println("<script src='./jss/readXML.js'></script>");
        out.println("<script src='./jss/admin.js'></script>");
        out.println("<link rel='stylesheet' href='./css/login.css' type='text/css'>");
        out.println("<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.0.12/css/all.css' integrity='sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9' crossorigin='anonymous'>");
        out.println("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
        out.println("<title>Document</title>");
        out.println("</head>");
        out.println("<body class='h-100'>");
        out.println("<nav class='navbar navbar-expand-lg navbar-dark' style='background: #3E6DB1'>");
        out.println("<a class='navbar-brand' href='#'>Navbar</a>");
        out.println("<ul class='navbar-nav mr-auto'>");
        out.println("<li class='nav-item active'>");
        out.println("<a class='nav-link'>Inicio</a>");
        out.println("</li>");
  
        out.println("</ul>");
        out.println("<ul class='navbar-nav ml-auto'>");
        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='login.html'><i class='fas fa-sign-out-alt'></i></a>");
        out.println("</li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("<div class='container-fluid h-100'>");
        out.println("<div class='row justify-content-center h-100'>");
        out.println("<div class='col-2' style='background: rgb(33, 37, 41)'>");
        
        out.println("<div class='row justify-content-center'>");
        out.println("<div class='pb-2 mt-4 mb-2 border-bottom' style='font-family: Snell Roundhand, cursive;' align='center'>");
        out.println("<h3><b>StoryOnline</b></h3>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='row justify-content-center'>");
        out.println("<div class='pb-2 mt-4 mb-2' >");
        out.println("<div class='container'>");
        out.println("<ul class='nav flex-column'>");
        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='AltasBajasCambios'>Altas, Bajas y Cambios</a>");
        out.println("</li>");
        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='Groups'>Agregar Grupos</a>");
        out.println("</li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='col-10' style='background: rgb(255, 255, 255)'>");
        out.println("<div class='container'>");
        out.println("<br/>");
     
        out.println("<h1>Grupos</h1>");

        out.println("<button class='btn btn-primary' id='nuevogrupo'><i class='fas fa-plus'></i></button>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<div class'row'>");
        out.println("<table class='table table-dark'>");
        out.println("<thead>");
        out.println("<tr class='bg-primary'>");
        out.println("<th>Grupo</th>");
        out.println("<th>Profesor</th>");
        out.println("<th>    </th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        
        for (Group u : l) {
            out.println("<tr>");
                out.println("<td>"+u.grupo+"</td>");
                out.println("<td>"+u.profesor+"</td>");
                out.println("<td class = 'text-center'>");
                    out.println("<button name ='"+u.grupo+"' class='btn btn-warning' onclick='updateGroup(this.name)'><i class='fas fa-pencil-alt'></i></button>");
                    out.println("<button name ='"+u.grupo+"' class='btn btn-info' onclick='addAlumns(this.name)'><i class='fas fa-user-plus '></i></button>");
                    out.println("<button name ='"+u.grupo+"' class='btn btn-danger' onclick='deleteGroup(this.name)'><i class='fas fa-trash-alt'></i></button>");
                out.println("</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        ////
         out.println("<div id='modalCont'></div>");
        out.println("<script src='./jss/groups.js'></script>");

        ///
        out.println("</body>");
        out.println("</html>");        
    }
}
