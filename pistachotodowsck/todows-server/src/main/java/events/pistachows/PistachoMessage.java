package events.pistachows;

import com.google.gson.Gson;
import pistachotodo.ToDoTask;

/**
 * Created by Administrador on 04/12/2014.
 */
public class PistachoMessage {

    private int code = -1;
    private int index = -1;
    private ToDoTask task = null;

    public PistachoMessage(){}

    public int getCode(){
        return code;
    }

    public int getIndex(){
        return index;
    }

    public ToDoTask getTask() {
        return task;
    }

    public static PistachoMessage parseFrom(String data){
        Gson gson = new Gson();

        PistachoMessage msg = gson.fromJson(data, PistachoMessage.class);
        return msg;
    }
}
