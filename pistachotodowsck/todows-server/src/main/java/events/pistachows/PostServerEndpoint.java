package events.pistachows;

import com.google.gson.JsonSyntaxException;
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

@ServerEndpoint(value = "/post")
public class PostServerEndpoint {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
	}

	@OnMessage
	public String onMessage(String message, Session session) {
        logger.info("Adding task: "+message);
        return addTask(TaskListManager.parseTask(message));
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s closed because of %s",
				session.getId(), closeReason));
	}

    private String addTask(ToDoTask newTask) {
        if (newTask != null && TaskListManager.isValid(newTask)) {
            logger.info("User added a task.");
            return TaskListManager.addTask(newTask);
        } else {
            logger.info("Failed to add a task.");
            return null;
        }
    }
}
