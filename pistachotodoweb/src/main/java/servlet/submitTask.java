package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pistachotodo.AddToDoTask;
import pistachotodo.ListToDoTasks;
import pistachotodo.ToDoList;
import pistachotodo.ToDoTask;

/**
 * Servlet implementation class submitTask
 */
@WebServlet(urlPatterns = { "/submitTask" })
public class submitTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submitTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ToDoList taskList = ListToDoTasks.getTaskList();
		if(taskList == null){
			taskList = new ToDoList();
		}
		ToDoTask newTask = new ToDoTask();
		
		if(request.getParameter("task")==null){
			newTask.setTask(" ");
		}else{
			newTask.setTask(request.getParameter("task"));
		}
		
		if(request.getParameter("context")==null){
			newTask.setContext(" ");
		}else{
			newTask.setContext(request.getParameter("context"));
		}
		
		if(request.getParameter("project")==null){
			newTask.setProject(" ");
		}else{
			newTask.setProject(request.getParameter("project"));
		}
		
		if(request.getParameter("priority")==null){
			newTask.setPriority(0);
		}else{
			newTask.setPriority(Integer.valueOf(request.getParameter("priority")));
		}
		
		taskList.addTask(newTask);
		
		AddToDoTask.saveTaskList(taskList);
	}

}
