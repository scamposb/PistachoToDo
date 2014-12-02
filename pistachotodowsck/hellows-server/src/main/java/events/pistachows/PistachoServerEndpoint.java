package events.pistachows;

import pistachotodo.TaskListManager;
import pistachotodo.ToDoList;
import pistachotodo.ToDoTask;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/PistachoToDo")
public class PistachoServerEndpoint {

	private Logger logger = Logger.getLogger(this.getClass().getName());

    private static final int START = 0;
    private static final int QUIT = 1;
    private static final int GET = 2;
    private static final int POST = 3;
    private static final int DELETE = 4;

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
	}

	@OnMessage
	public String onMessage(String message, Session session) {
        try {
            message = message.trim();
            int code = Integer.parseInt(message.substring(0, 1));
            switch (code) {
                case START:
                    return "Hello, not-so-Anon";
                case QUIT:
                    try {
                        session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE,
                                "Connection ended"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                case GET:
                    //Returns a JSON with ToDoTasks
                    logger.info("User asked for TaskList");
                    return TaskListManager.getJsonTaskList();
                case POST:
                    //Saves a ToDoTask
                    String DATA = message.substring(2);
                    ToDoTask newTask = TaskListManager.parseTask(DATA);
                    if(newTask != null) {
                        TaskListManager.addTask(newTask);
                        logger.info("User added a task");
                        return "Task added";
                    }else{
                        logger.info("Failed to add a task");
                        return "Syntax of task: "+ DATA + ", is invalid.";
                    }
                case DELETE:
                    //Saves a ToDoTask
                    return "WIP, try later";
                default:
                    logger.info("Unrecognized message: " + message + " \n" +
                            "From: " + session.getId());
                    return "Anon please";
            }
        }catch(NumberFormatException e){
            logger.info("Anon sent bad code, such fag");
            return "Bad code >:C";
        }catch(StringIndexOutOfBoundsException e){
            logger.info("Parameters for invoked method wrongly specified");
            return "Specify parameters, fag";
        }
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s closed because of %s",
				session.getId(), closeReason));
	}
}
