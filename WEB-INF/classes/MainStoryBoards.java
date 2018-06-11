import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;



public class MainStoryBoards extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("user");
        String ruta = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
        PrintWriter out = response.getWriter();
        ListVideos lv = new ListVideos(ruta);
        List<Video> videos = lv.generateList();
        ListTextos lt = new ListTextos(ruta);
        List<Texto> textos = lt.generateList();
        ListStoryboards lc = new ListStoryboards(ruta);
        List<CStoryBoard> stories = lc.generateList();
        ListImages li = new ListImages(ruta);
        List<Imagen> imagenes = li.generateList();
        ListAudios la = new ListAudios(ruta);
        List<Audio> audios = la.generateList();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es' style = 'min-height: 100%;'>");
        out.println("<head>");
        out.println("<title>Administrador</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.1.0/fabric.all.min.js' ></script>");
        out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
        out.println("<script src='./jss/readXML.js'></script>");
        out.println("<script src='./jss/profesor.js'></script>");
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
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='row justify-content-center'>");
        out.println("<div class='pb-2 mt-4 mb-2' >");
        out.println("<div class='container'>");
        out.println("<ul class='nav flex-column'>");
        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='MainStoryBoards'>Storyboards</a>");
        out.println("</li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='col-10' style='background: rgb(255, 255, 255)'>");
        out.println("<div class='container'>");
        out.println("<br/>"); 
        out.println("<h1>Mis StoryBoards</h1>");
        out.println("<a href='CreateStoryBoard'><button class='btn btn-primary'><i class='fas fa-plus'></i></button></a>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<h1>Videos</h1>");


        out.println("<div class='row'>");
        
        int cont = 0;
        for (Video v : videos) {
            String name = v.name;
            out.println("<div class='col-3'>");
            out.println("<div class='card '>");
            out.println("<div class='card-header text-left'>");
            out.println("<i class='fas fa-file-video'></i>");
            out.println("</div>");
            out.println("<div class='card-body text-center'>");
            out.println("<h5 class='card-title'>"+name+"</h5>");
            out.println("<p class='card-text'><img src='./img/video.png' class='img-fluid' width='50'>");
            out.println("</div>");
            out.println("<div class='card-footer text-muted text-center'>");
             /////
             out.println("<button name ='"+name+"'class='btn btn-info' onclick='getComments(this.name);'><i class='fas fa-comments'></i></button>");
             ///
            out.println("<a href='playvideo?ruta="+v.video+"&name="+name+"'><button name ='"+v.video+"'class='btn btn-primary' onclick=''><i class='fas fa-play'></i></button></a>");
            out.println("<button name ='"+name+"'class='btn btn-danger' onclick='confirmvideo(this.name);'><i class='fas fa-trash-alt'></i></button>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            if ((cont+1)%4== 0) {
                out.println("</div>");
                out.println("<div class='row'>");
            }
        }
        if ((cont+1)%4 != 0) {
            out.println("</div>");
        }
        out.println("<h1>Textos</h1>");
        out.println("<div class='row'>");
        
        cont = 0;
        for (Texto t : textos) {
            out.println("<div class='col-3'>");
            out.println("<div class='card'>");
            out.println("<div class='card-header text-left' >");
            out.println("<i class='fas fa-file-alt'></i>");
            out.println("</div>");
            out.println("<div class='card-body text-center'>");
            out.println("<h5 class='card-title'>"+t.name+"</h5>");
            out.println("<p class='card-text'><i class='far fa-file-alt' style =' font-size:3em;'></i>");
            out.println("</div>");
            out.println("<div class='card-footer text-muted text-center'>");
             /////
             out.println("<button name ='"+t.name+"'class='btn btn-info' onclick='getComments(this.name);'><i class='fas fa-comments'></i></button>");
             ///
            out.println("<button name ='"+t.name+"'class='btn btn-warning' onclick='modifytexto(this.name);'><i class='fas fa-pencil-alt'></i></button>");
            out.println("<button name ='"+t.name+"'class='btn btn-danger' onclick='confirmtext(this.name);'><i class='fas fa-trash-alt'></i></button>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            if ((cont+1)%4 == 0) {
                out.println("</div>");
                out.println("<div class='row'>");
            }
        }
        if ((cont+1)%4 != 0) {
            out.println("</div>");
        }
        cont = 0;
        out.println("<h1>Storyboards</h1>");
        out.println("<div class='row'>");
        for (CStoryBoard s : stories) {
            out.println("<div class='col-3'>");
            out.println("<div class='card'>");
            out.println("<div class='card-header text-left' >");
            out.println("<i class='fas fa-images'></i>");
            out.println("</div>");
            out.println("<div class='card-body text-center'>");
            out.println("<h5 class='card-title'>"+s.name+"</h5>");
            out.println("<p class='card-text'><i class='fas fa-image' style =' font-size:3em;'></i>");
            out.println("</div>");
            out.println("<div class='card-footer text-muted text-center'>");
            /////
             out.println("<button name ='"+s.name+"' class='btn btn-info' onclick='getComments(this.name);'><i class='fas fa-comments'></i></button>");
             ///
            out.println("<button name ='"+s.name+"'class='btn btn-warning' onclick='modifystory(this.name);'><i class='fas fa-pencil-alt'></i></button>");
            out.println("<button name ='"+s.name+"'class='btn btn-danger' onclick='confirmstory(this.name);'><i class='fas fa-trash-alt'></i></button>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            if ((cont+1)%4 == 0) {
                out.println("</div>");
                out.println("<div class='row'>");
            }
        }
        if ((cont+1)%4 != 0) {
            out.println("</div>");
        }

        out.println("<h1>Imagenes</h1>");
        out.println("<div class='row'>");
        for (Imagen s : imagenes) {
            out.println("<div class='col-3'>");
            out.println("<div class='card'>");
            out.println("<div class='card-header text-left' >");
            out.println("<i class='fas fa-images'></i>");
            out.println("</div>");
            out.println("<div class='card-body text-center'>");
            out.println("<h5 class='card-title'>"+s.name+"</h5>");
            out.println("<p class='card-text'><img src='."+s.imagen+"' alt='' class='img-responsive' width='80' height='80'/></i>");
            out.println("</div>");
            out.println("<div class='card-footer text-muted text-center'>");
            out.println("<button name ='"+s.name+"'class='btn btn-danger' onclick='confirmimagen(this.name);'><i class='fas fa-trash-alt'></i></button>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            if ((cont+1)%4 == 0) {
                out.println("</div>");
                out.println("<div class='row'>");
            }
        }
        if ((cont+1)%4 != 0) {
            out.println("</div>");
        }

        out.println("<h1>Audios</h1>");
        out.println("<div class='row'>");
        for (Audio s : audios) {
            out.println("<div class='col-4'>");
            out.println("<div class='card'>");
            out.println("<div class='card-header text-left' >");
            out.println("<i class='fas fa-images'></i>");
            out.println("</div>");
            out.println("<div class='card-body text-center'>");
            out.println("<h5 class='card-title'>"+s.name+"</h5>");
            out.println("<p class='card-text'> <audio controls style = 'width: 100px;'><source src='."+s.audio+"' type='audio/mp3'></audio>");
            out.println("</div>");
            out.println("<div class='card-footer text-muted text-center'>");
            out.println("<button name ='"+s.name+"'class='btn btn-danger' onclick='confirmaudio(this.name);'><i class='fas fa-trash-alt'></i></button>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            if ((cont+1)%3 == 0) {
                out.println("</div>");
                out.println("<div class='row'>");
            }
        }
        if ((cont+1)%3 != 0) {
            out.println("</div>");
        }

        out.println("<br/>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        ////
         out.println("<div id='modalCont'></div>");
        out.println("<script src='./jss/studentsNotes.js'></script>");

        ///
        out.println("</body>");
        out.println("</html>");        
    }
}
