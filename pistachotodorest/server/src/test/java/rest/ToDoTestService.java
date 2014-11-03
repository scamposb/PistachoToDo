package rest;

import static org.junit.Assert.*;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.junit.After;
import org.junit.Test;
import pistachotodo.ToDoList;
import pistachotodo.ToDoTask;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Created by David Recuenco on 02/11/2014.
 */
public class ToDoTestService {

    HttpServer server;

    @Test
    public void serviceIsAlive() throws IOException {
        // Prepare server
        ToDoList tl = new ToDoList();
        launchServer(tl);

        // Request the address book
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8282/PistachoToDo")
                .request().get();
        assertEquals(200, response.getStatus());
        assertEquals(0, response.readEntity(ToDoList.class).getToDoList()
                .size());
    }

    @Test
    public void createTask() throws IOException {
        // Prepare server
        ToDoList tl = new ToDoList();
        launchServer(tl);

        // Prepare data
        ToDoTask newTask = new ToDoTask();
        newTask.setTask("TestTask");
        newTask.setContext("TestContext");
        newTask.setProject("TestProject");
        newTask.setPriority(1);
        URI newTaskURI = URI.create("http://localhost:8282/PistachoToDo/task/1");

        // Create a new user
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8282/PistachoToDo")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newTask, MediaType.APPLICATION_JSON));

        assertEquals(201, response.getStatus());
        assertEquals(newTaskURI, response.getLocation());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        ToDoTask newTaskUpdated = response.readEntity(ToDoTask.class);
        assertEquals(newTask.getTask(), newTaskUpdated.getTask());
        assertEquals(newTask.getContext(), newTaskUpdated.getContext());
        assertEquals(newTask.getProject(), newTaskUpdated.getProject());
        assertEquals(newTask.getPriority(), newTaskUpdated.getPriority());
        assertEquals(1, newTaskUpdated.getId());
        assertEquals(newTaskURI, newTaskUpdated.getHref());

        // Check that the new user exists
        response = client.target("http://localhost:8282/PistachoToDo/task/1")
                .request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        newTaskUpdated = response.readEntity(ToDoTask.class);
        assertEquals(newTask.getTask(), newTaskUpdated.getTask());
        assertEquals(newTask.getContext(), newTaskUpdated.getContext());
        assertEquals(newTask.getProject(), newTaskUpdated.getProject());
        assertEquals(newTask.getPriority(), newTaskUpdated.getPriority());
        assertEquals(1, newTaskUpdated.getId());
        assertEquals(newTaskURI, newTaskUpdated.getHref());

    }

    @Test
    public void listTasks() throws IOException {

        // Prepare server
        ToDoList tl = new ToDoList();
        ToDoTask newTask1 = new ToDoTask();
        newTask1.setTask("TestTask1");
        newTask1.setContext("TestContext1");
        newTask1.setProject("TestProject1");
        newTask1.setPriority(2);
        ToDoTask newTask2 = new ToDoTask();
        newTask2.setTask("TestTask2");
        newTask2.setContext("TestContext2");
        newTask2.setProject("TestProject2");
        newTask2.setPriority(2);
        tl.getToDoList().add(newTask1);
        tl.getToDoList().add(newTask2);
        launchServer(tl);

        // Test list of contacts
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8282/PistachoToDo")
                .request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        ToDoList toDoListRetrieved = response
                .readEntity(ToDoList.class);
        assertEquals(2, toDoListRetrieved.getToDoList().size());
        assertEquals(newTask2.getTask(), toDoListRetrieved.getToDoList()
                .get(1).getTask());
        assertEquals(newTask2.getContext(), toDoListRetrieved.getToDoList()
                .get(1).getContext());
        assertEquals(newTask2.getProject(), toDoListRetrieved.getToDoList()
                .get(1).getProject());
        assertEquals(newTask2.getPriority(), toDoListRetrieved.getToDoList()
                .get(1).getPriority());
    }



    private void launchServer(ToDoList tl) throws IOException {
        URI uri = UriBuilder.fromUri("http://localhost/").port(8282).build();
        server = GrizzlyHttpServerFactory.createHttpServer(uri,
                new ApplicationConfig(tl));
        server.start();
    }

    @After
    public void shutdown() {
        if (server != null) {
            server.shutdownNow();
        }
        server = null;
    }
}
