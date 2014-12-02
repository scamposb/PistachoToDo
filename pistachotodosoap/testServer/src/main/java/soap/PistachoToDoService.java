package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import pistachotodo.*;

@WebService
public class PistachoToDoService {

    @WebMethod
    public void addToDo (String task, String context, String project, int priority) {
        TaskListManager.addTask(task, context, project, priority);
    }

    @WebMethod
    public void removeToDo (String task, String context, String project, int priority) {
        TaskListManager.removeTask(task, context, project, priority);
    }

    @WebMethod
    public ToDoList listToDo () {
        return TaskListManager.getTaskList();
    }
}
