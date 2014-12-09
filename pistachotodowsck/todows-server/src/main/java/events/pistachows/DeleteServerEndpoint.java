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

@ServerEndpoint(value = "/delete")
public class DeleteServerEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        logger.info("Deleting task: "+message);
        return removeTask(message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s",
                session.getId(), closeReason));
    }

    private String removeTask(String message){
        try{
            int index = Integer.parseInt(message);
            TaskListManager.removeTask(index);
            logger.info("User deleted a task.");
            return null;
        }catch(Exception e){
            logger.info("Failed to remove a task.");
            return null;
        }
    }
}
