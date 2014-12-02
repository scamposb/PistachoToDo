package pistachotodo;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    private int nextId = 1;
	private List<ToDoTask> taskList = new ArrayList<ToDoTask>();

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
	
	public void addTask(ToDoTask task){
		taskList.add(task);
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
