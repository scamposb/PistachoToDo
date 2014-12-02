package soap;

import pistachotodo.TaskListManager;
import pistachotodo.ToDoList;
import pistachotodo.ToDoTask;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by David Recuenco on 25/10/2014.
 */

@WebService
public class PistachoToDoService {

    @WebMethod
    public ToDoList getTaskList(){
        return TaskListManager.getTaskList();
    }

    @WebMethod
    public void saveTaskList(ToDoList taskList, ToDoTask task){
        taskList.addTask(task);
        TaskListManager.saveTaskList(taskList);
    }
}
