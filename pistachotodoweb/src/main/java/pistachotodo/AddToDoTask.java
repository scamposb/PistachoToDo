package pistachotodo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.Gson;

public class AddToDoTask {

	public final static String DEFAULT_FILE_NAME = "todo_task_list.json";

	// This function fills in a new ToDoTask based on user input.
	static ToDoTask PromptForAddress(BufferedReader stdin, PrintStream stdout)
			throws IOException {
		ToDoTask task = new ToDoTask();

		stdout.print("Enter task name: ");
		task.setTask(stdin.readLine());

		stdout.print("Enter task context: ");
		task.setContext(stdin.readLine());

		stdout.print("Enter task project: ");
		task.setProject(stdin.readLine());

		stdout.print("Enter task priority (number): ");
		int priority = 0;
		try {
			priority = Integer.valueOf(stdin.readLine());
		} catch (Exception e) {
			stdout.println(">NaN, task set to default priority");
		}
		task.setPriority(priority);

		return task;
	}
	
	public static void saveTaskList(ToDoList taskList) throws IOException{
		Gson gson = new Gson();
		
		FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
		output.write(gson.toJson(taskList));
		File f = new File(DEFAULT_FILE_NAME);
		System.out.println(f.getAbsolutePath());
		output.close();
		System.out.println("Added new task.");
	}

	// Main function: Reads the entire address book from a file,
	// adds one person based on user input, then writes it back out to the same
	// file.
	public static void main(String[] args) throws Exception {
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}

		ToDoList taskList = new ToDoList();
		Gson gson = new Gson();

		// Read the existing address book.
		try {
			taskList = gson.fromJson(new FileReader(filename),
					ToDoList.class);
		} catch (FileNotFoundException e) {
			System.out.println(filename
					+ ": File not found.  Creating a new file.");
		}

		// Add an address.
		taskList.addTask(PromptForAddress(new BufferedReader(
				new InputStreamReader(System.in)), System.out));

		// Write the new address book back to disk.
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(taskList));
		output.close();
	}

}
