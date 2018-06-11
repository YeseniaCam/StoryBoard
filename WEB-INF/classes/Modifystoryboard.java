import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;


public class Modifystoryboard extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String tipo = request.getParameter("tipo");
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("user");
        String name = request.getParameter("nombre");
        String ruta = request.getSession().getServletContext().getRealPath("./icons");
        String rutaf = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
        Images imagenes =  new Images(ruta,"icons");
        List<Listas> totalimag = imagenes.generateLists();
        ListStoryboards ls = new ListStoryboards(rutaf);
        CStoryBoard story =  ls.getOneStory(name);
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es' class=''>");
        out.println("<head>");
        out.println("<title>Administrador</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
        out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
        out.println("<script src='./jss/readXML.js'></script>");
        out.println("<link rel='stylesheet' href='./css/login.css' type='text/css'>");
        out.println("<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.0.12/css/all.css' integrity='sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9' crossorigin='anonymous'>");  
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.1.0/fabric.all.min.js' ></script>");
        out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js'></script>");
        out.println("<script src='./jss/canvasmod.js'></script>");
        out.println("<script src='./jss/jscolor.js'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
        out.println("<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>");
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js' integrity='sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q' crossorigin='anonymous'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js' integrity='sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl' crossorigin='anonymous'></script>");
        out.println("<link rel='stylesheet' href='./css/css1.css'>");   
        out.println("<script>");
        out.println("var x = new Array()");
        for (int i = 0; i < story.slides.size(); i++) {
            out.println("x.push('"+story.slides.get(i)+"');");
        }
        out.println("</script>");
        out.println("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
        out.println("<title>Document</title>");
        out.println("</head>");
        out.println("<body class=''>");
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
        out.println("<div class='container-fluid '>");
        out.println("<div class='row justify-content-center '>");
        out.println("<div class='col-2 ' id = 'scroll' style='background: rgb(33, 37, 41)'>");
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
        out.println("<div class='col-10 ' style='background: rgb(255, 255, 255)'>");
        out.println("<div class='container '>");
        out.println("<br/>");
        out.println("<div class='container-fluid'>");
        out.println("<div class='page-header'>");
        out.println("<h1>"+name+"</h1>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='container-fluid'>");
        out.println("<div class='text-center'>");
        out.println("<div class='row'>");
        out.println("<div class='col-sm-1 my-auto'>");
        out.println("<button id='bprev' class='btn btn-info'>");
        out.println("<i class='fas fa-arrow-left'></i>");
        out.println("</button>");
        out.println("</div>");
        out.println("<div class='col-sm-10 my-auto'>");
        out.println("<canvas id='canvas'style='border:1px solid black;' width='820' height='550'></canvas>");
        out.println("</div>");
        out.println("<div class='col-sm-1 my-auto'>");
        out.println("<button id='bsig' class='btn btn-info'>");
        out.println("<i class='fas fa-arrow-right'></i>");
        out.println("</button>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='container-fluid'>");
        out.println("<div class='text-center'>");
        out.println("<p id ='numpa'>Página: 1<p>");
        out.println("</div>");
        out.println("</div>");
        out.println("<br/>");
        out.println("Color de la Figura: <input id='shapecolor' class='jscolor' val ='ff0000' size ='2'>");
        out.println("<button id='clearboard' class='btn btn-danger' ><i class='fas fa-trash-alt'></i></button>");
        out.println("<button id='delete' class='btn btn-danger'><i class='fas fa-eraser'></i></button>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<div class='container-fluid'>");
        out.println("<button id ='button1' class='btn btn-info iconos'>Circulo</button>");
        out.println("<button id ='button2' class='btn btn-info iconos'>Rectangulo</button>");
        out.println("<button id ='button3' class='btn btn-info iconos'>Linea</button>");
        out.println("<button id ='button4' class='btn btn-info iconos'>Texto</button>");
        out.println("Ingresa el texto: <input type='text' size = '30' id='textocanvas'/>");
        // out.println("<div class='text-center'>");
        out.println("<ul class='nav nav-tabs' role='tablist'>");
        for (int i = 0; i < totalimag.size(); i++) {
            Listas l = totalimag.get(i);
            out.println("<li role='presentation' class='active'><a href='#"+l.name+"' aria-controls='"+l.name+"' role='tab' data-toggle='tab'>"+l.name+"</a>");
        }
        out.println("</ul>");
        out.println("<div class='tab-content'>");
        for (int i = 0; i < totalimag.size(); i++) {
            Listas l = totalimag.get(i);
            List<String> pnglist = (List<String>)l.PNGlList;
            out.println("<div role='tabpanel' class='tab-pane ' id='"+l.name+"'>");

            for (int j = 0; j < pnglist.size(); j++) {
                String r = pnglist.get(j);
                out.println("<div id ='1' class='btn btn-info iconos' name='"+r+"'><img src='"+r+"' alt='' class='img-responsive' width='60' height='60' draggable='true' /></div>");
            }

            out.println("</div>");
        }
        out.println("</div>");
        // out.println("</div>");
        out.println("</div>");
        out.println("<form action=./UpdateStoryBoard method ='post'>");
        out.println("<input  id='slides' type='text' name='slides' size='10'/>");
        out.println("<input hidden type='text' name='modificable' value = '"+name+"'/>");
        out.println("<button id='save' type='submit' class ='btn btn-success'>Save</button>");
        out.println("</form>");

        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");        
             
    }
}