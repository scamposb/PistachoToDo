package pistachotodo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class TaskListManager {

	public final static String DEFAULT_FILE_NAME = "todo_task_list.json";

    public static String getJsonTaskList() {
        ToDoList taskList = new ToDoList();
        Gson gson = new Gson();

        // Read the existing task list.
        try {
            taskList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME),
                    ToDoList.class);
        } catch (FileNotFoundException e) {
            taskList = null;
        }
        return gson.toJson(taskList);
    }

	public static ToDoList getTaskList() {
		ToDoList taskList = new ToDoList();
		Gson gson = new Gson();

		// Read the existing task list.
		try {
			taskList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME),
					ToDoList.class);
		} catch (FileNotFoundException e) {
			taskList = new ToDoList();
		}
		return taskList;
	}

    public static void saveTaskList(ToDoList taskList) {
        Gson gson = new Gson();

        try {
            FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
            output.write(gson.toJson(taskList));
//		    File f = new File(DEFAULT_FILE_NAME);
//		    System.out.println(f.getAbsolutePath());
            output.close();
            System.out.println("Added new task.");
        }catch(IOException e){
            System.err.println("-- This should not happen.");
        }
    }

    public static void addTask (String task, String context, String project, int priority){
        ToDoTask newTask = new ToDoTask();
        newTask.setTask(task);
        newTask.setContext(context);
        newTask.setProject(project);
        newTask.setPriority(priority);
        ToDoList list = getTaskList();
        list.addTask(newTask);

        saveTaskList(list);
    }

    public static void addTask (ToDoTask task){
        ToDoList list = getTaskList();
        list.addTask(task);

        saveTaskList(list);
    }

    public static void removeTask (String task, String context, String project, int priority){
        ToDoTask newTask = new ToDoTask();
        newTask.setTask(task);
        newTask.setContext(context);
        newTask.setProject(project);
        newTask.setPriority(priority);
        ToDoList list = getTaskList();
        list.removeTask(newTask);

        saveTaskList(list);
    }

    public static void removeTask (int index){
        ToDoList list = getTaskList();
        for (int i = 0; i < list.getToDoList().size(); i++) {
            if (list.getToDoList().get(i).getId() == index) {
                list.getToDoList().remove(i);
                System.out.println("Deleted task.");
            }
        }

        saveTaskList(list);
    }

    public static boolean isValid(ToDoTask task){
        return (task.getTask() != null &&
                !task.getTask().isEmpty());
    }

}
