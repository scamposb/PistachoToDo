package pistachotodo;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
	
	private List<ToDoTask> taskList = new ArrayList<ToDoTask>();
	
	public List<ToDoTask> getToDoList() {
		return taskList;
	}
	
	public void setToDoList(List<ToDoTask> taskList){
		this.taskList = taskList;
	}
	
	public void addTask(ToDoTask task){
		taskList.add(task);
	}

}
