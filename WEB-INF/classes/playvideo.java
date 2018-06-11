
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;


public class playvideo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String name = request.getParameter("name");      
        String user = (String)session.getAttribute("user");     
            
            
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es' style = 'min-height: 100%;'>");
        out.println("<head>");
        out.println("<title>Profesor</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
        out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
        out.println("<script src='./jss/readXML.js'></script>");
        out.println("<link rel='stylesheet' href='./css/login.css' type='text/css'>");
        out.println("<link href='https://vjs.zencdn.net/7.0.3/video-js.css' rel='stylesheet'>");
        out.println("<script src='https://vjs.zencdn.net/7.0.3/video.js'></script>");
        out.println("<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.0.12/css/all.css' integrity='sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9' crossorigin='anonymous'>");  
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.1.0/fabric.all.min.js' ></script>");
        out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js'></script>");
        out.println("<script src='./jss/canvas.js'></script>");
        out.println("<script src='./jss/jscolor.js'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
        out.println("<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>");
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js' integrity='sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q' crossorigin='anonymous'></script>");
        out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js' integrity='sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl' crossorigin='anonymous'></script>");
        out.println("<link rel='stylesheet' href='./css/css1.css'>");   
        out.println("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
        out.println("<title>Document</title>");
        out.println("</head>");
        out.println("<body >");
        out.println("<nav class='navbar navbar-expand-lg navbar-dark' style='background: #3E6DB1'>");
        out.println("<a class='navbar-brand' href='#'>Navbar</a>");
        out.println("<ul class='navbar-nav mr-auto'>");
        out.println("<li class='nav-item active'>");
        out.println("<a class='nav-link' href='./Profesor?user='"+user+"'>Inicio</a>");
        out.println("</li>");

        out.println("</ul>");
        out.println("<ul class='navbar-nav ml-auto'>");
        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='login.html'><i class='fas fa-sign-out-alt'></i></a>");
        out.println("</li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("<div class='container-fluid '>");
        out.println("<div class='row justify-content-center'>");
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
        out.println("<br/>");
        out.println("<h1>"+name+"</h1>");        
       
        out.println("<div class='row justify-content-center'>");
        
        out.println("<video id='my-video' class='video-js' controls preload='auto' width='760' height='480'");
        out.println("poster='' data-setup='{}'>");
        out.println("<source src='."+request.getParameter("ruta")+"' type='video/mp4'>");
        out.println("</video>");

        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("</body>");
        out.println("</html>");
    }
}