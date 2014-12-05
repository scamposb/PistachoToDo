package events.pistachows;

import com.google.gson.Gson;

/**
 * Created by Administrador on 05/12/2014.
 */
public class PistachoResponse {

    /* CODES */
    public static final int BAD_SYNTAX = 0;
    public static final int BAD_CODE = 1;
    public static final int BAD_TASK = 2;
    public static final int BAD_INDEX = 3;
    public static final int CORRECT = 4;
    public static final int HELLO = 5;

    /* MESSAGES */
    private static final String BAD_SYN_MSG = "The request is unacceptable, check syntax";
    private static final String BAD_COD_MSG = "Requested operation unavailable";
    private static final String BAD_TSK_MSG = "Task syntax wrong";
    private static final String BAD_IDX_MSG = "Task index wrong";
    private static final String CORRECT_MSG = "Operation successful";
    private static final String HELLO_MSG = "Hello world!";

    /* Attributes */
    private int code;
    private String message;

    public PistachoResponse(int code){
        this.code = code;
        switch (code){
            case BAD_SYNTAX:
                this.message = BAD_SYN_MSG;
                break;
            case BAD_CODE:
                this.message = BAD_COD_MSG;
                break;
            case BAD_TASK:
                this.message = BAD_TSK_MSG;
                break;
            case BAD_INDEX:
                this.message = BAD_IDX_MSG;
            case CORRECT:
                this.message = CORRECT_MSG;
            case HELLO:
                this.message = HELLO_MSG;
        }
    }

    public String JSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
