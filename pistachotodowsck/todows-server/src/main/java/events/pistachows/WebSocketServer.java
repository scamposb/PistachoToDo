package events.pistachows;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.tyrus.server.Server;
 
public class WebSocketServer {
	private static final Logger LOGGER = Grizzly.logger(Server.class);
 
    public static void main(String[] args) {
        runServer();
    } 
 
    public static void runServer() {
        Server server2 = new Server("localhost", 8025, "/websockets", new HashMap<String,Object>(), ListServerEndpoint.class);
        Server server = new Server("localhost", 8026, "/websockets", new HashMap<String,Object>(), PostServerEndpoint.class);
        Server server3 = new Server("localhost", 8027, "/websockets", new HashMap<String,Object>(), DeleteServerEndpoint.class);

        try {
            server.start();
            server2.start();
            server3.start();
			LOGGER.info("Press 's' to shutdown now the server...");
			while(true){
				int c = System.in.read();
				if (c == 's')
					break;
			}
        } catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            server.stop();
            server2.stop();
            server3.stop();
			LOGGER.info("Server stopped");
        }
    }
}
