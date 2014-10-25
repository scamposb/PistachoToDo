package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import soap.ToDoTask;
import soap.ToDoList;
import soap.PistachoToDoService;
import soap.PistachoToDoServiceService;

/**
 * Servlet implementation class loadServlet
 */
@WebServlet(urlPatterns = { "/loadServlet" })
public class loadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PistachoToDoServiceService ptdss = new PistachoToDoServiceService();
        PistachoToDoService ptds = ptdss.getPistachoToDoServicePort();
		ToDoList taskList = ptds.getTaskList();
		if(taskList!=null){
			request.setAttribute("taskList", taskList);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}

}
