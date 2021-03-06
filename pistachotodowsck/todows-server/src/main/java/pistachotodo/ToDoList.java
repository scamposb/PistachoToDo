package pistachotodo;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    private static final Gson gson = new Gson();

    private int nextId = 1;
	private List<ToDoTask> taskList = new ArrayList<ToDoTask>();

    public ToDoList(){}


    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public int nextId() {
        int oldValue = nextId;
        nextId++;
        return oldValue;
    }

	public List<ToDoTask> getToDoList() {
		return taskList;
	}
	
	public void setToDoList(List<ToDoTask> taskList){
		this.taskList = taskList;
	}
	
	public ToDoTask addTask(ToDoTask task){
        task.setId(nextId());
		taskList.add(task);
        return task;
	}

    public void removeTask(ToDoTask task){
        for(ToDoTask itask : taskList){
            if(itask.getTask().equals(task.getTask()) &&
                    itask.getContext().equals(task.getContext()) &&
                    itask.getProject().equals(task.getProject()) &&
                    itask.getPriority()==task.getPriority()){
                taskList.remove(itask);
                System.out.println("Deleted task");
            }
        }
    }

}
