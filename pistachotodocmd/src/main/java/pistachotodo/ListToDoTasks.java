package pistachotodo;

import java.io.FileReader;

import com.google.gson.Gson;

public class ListToDoTasks {

	public final static String DEFAULT_FILE_NAME = "todo_task_list.json";

	// Iterates though all tasks in the ToDoList and prints info about them.
	static void Print(ToDoList taskList) {
		for (ToDoTask task : taskList.getToDoList()) {
			System.out.println("---------------------------------------------------");
			System.out.println("Task ID: " + task.getTask());
			System.out.println("  Task Context: " + task.getContext());
			System.out.println("  Task Project: " + task.getProject());
			System.out.println("  Task Priority: " + task.getPriority());
		}
		System.out.println("---------------------------------------------------");
	}

	// Main function: Reads the entire task list from a file and prints all
	// the information inside.
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}

		// Read the existing task list.
		ToDoList taskList = gson.fromJson(new FileReader(filename),
				ToDoList.class);

		Print(taskList);
	}

}
