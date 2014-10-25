package soap;

import javax.xml.ws.Endpoint;

/**
 * Created by David Recuenco on 25/10/2014.
 */
public class Server {

    public static void main (String[] args){
        Endpoint.publish("http://localhost:8081/PistachoToDo", new PistachoToDoService());
    }
}
