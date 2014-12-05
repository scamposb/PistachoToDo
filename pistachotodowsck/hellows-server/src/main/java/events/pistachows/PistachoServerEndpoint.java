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
        try{
            logger.info(message);
            PistachoMessage msg = PistachoMessage.parseFrom(message);
            logger.info("\n" +
                    "==============================\n" +
                    "==============================\n" +
                    "\tcode: "+msg.getCode()+"\n" +
                    "\tindex: "+msg.getIndex()+"\n" +
                    "\ttask: "+msg.getTask()+"\n" +
                    "==============================\n" +
                    "==============================\n");
            switch(msg.getCode()){
                case START:
                    logger.info("Saying hello to user.");
                    return new PistachoResponse(PistachoResponse.HELLO).JSON();
                case QUIT:
                    close(session);
                    return null;
                case GET:
                    //Returns a JSON with ToDoTasks
                    logger.info("User asked for TaskList.");
                    return TaskListManager.getJsonTaskList();
                case POST:
                    //Saves a ToDoTask
                    return addTask(msg.getTask());
                case DELETE:
                    //Deletes a ToDoTask
                    return removeTask(msg.getIndex());
                default:
                    logger.info("Unrecognized message: " + message + " \n" +
                            "From: " + session.getId());
                    return new PistachoResponse(PistachoResponse.BAD_CODE).JSON();
            }
        }catch(NullPointerException e){
            logger.info("Bad code on request");
            return new PistachoResponse(PistachoResponse.BAD_CODE).JSON();
        }catch(Exception e){
            logger.info("Bad syntax on request");
            return new PistachoResponse(PistachoResponse.BAD_SYNTAX).JSON();
        }
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s closed because of %s",
				session.getId(), closeReason));
	}

    private void close(Session session){
        try {
            session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE,
                    "Connection ended."));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String addTask(ToDoTask newTask){
        if(newTask != null && TaskListManager.isValid(newTask)) {
            TaskListManager.addTask(newTask);
            logger.info("User added a task.");
            return new PistachoResponse(PistachoResponse.CORRECT).JSON();
        }else{
            logger.info("Failed to add a task.");
            return new PistachoResponse(PistachoResponse.BAD_TASK).JSON();
        }
    }

    private String removeTask(int index){
        try{
            ToDoList list = TaskListManager.getTaskList();
            for (int i = 0; i < list.getToDoList().size(); i++) {
                if (list.getToDoList().get(i).getId() == index) {
                    list.getToDoList().remove(i);
                    System.out.println("Deleted task.");
                }
            }
            TaskListManager.saveTaskList(list);
            return new PistachoResponse(PistachoResponse.CORRECT).JSON();
        }catch(Exception e){
            return new PistachoResponse(PistachoResponse.BAD_INDEX).JSON();
        }
    }
}
