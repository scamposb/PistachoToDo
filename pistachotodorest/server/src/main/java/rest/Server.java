package rest;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import pistachotodo.TaskListManager;
import pistachotodo.ToDoList;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by David Recuenco on 01/11/2014.
 */

public class Server {
    private static final Logger LOGGER = Grizzly.logger(Server.class);

    public static void main(String[] args) {
        LOGGER.setLevel(Level.FINER);
        ToDoList tl = new ToDoList();

        URI uri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri,
                new ApplicationConfig(tl));

        try {
            server.start();
            LOGGER.info("Press 's' to shutdown now the server...");
            while (true) {
                int c = System.in.read();
                if (c == 's')
                    break;
            }
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
        } finally {
            server.stop();
            LOGGER.info("Server stopped");
        }

    }
}
