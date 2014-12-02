package soap;

import javax.xml.ws.Endpoint;

public class Server {

    public static void main (String[] args){
        Endpoint.publish("http://localhost:8080/PistachoToDo", new PistachoToDoService());
    }
}
