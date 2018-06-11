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


public class DeleteStory extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String user = (String)session.getAttribute("user");
        String story = request.getParameter("story");
        String ruta = request.getSession().getServletContext().getRealPath("/Usuarios/"+user+"/base.xml");
        ListStoryboards stories = new ListStoryboards(ruta);
        CStoryBoard s = stories.getOneStory(story);
        stories.deleteStory();
        response.sendRedirect("MainStoryBoards");
    }
}
