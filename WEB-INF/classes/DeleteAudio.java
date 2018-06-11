import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Iterable;
import java.util.List;
import java.io.File;


public class DeleteAudio extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String user = (String)session.getAttribute("user");
        String audio = request.getParameter("audio");
        String ruta = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
        ListAudios audios = new ListAudios(ruta);
        Audio a = audios.getOneAudio(audio);
        audios.deleteAudio();
        try{
    		
    		File file = new File(request.getSession().getServletContext().getRealPath("/") + a.audio );
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    	   
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		
    	}
        response.sendRedirect("MainStoryBoards");
    }
}
