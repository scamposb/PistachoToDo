package rest;

import static org.junit.Assert.assertEquals;

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

    /**
     * Test suite to check that server is up and running
     * @throws IOException
     */
    @Test
    public void serviceIsAlive() throws IOException {
        // Prepare server
        ToDoList tl = new ToDoList();
        launchServer(tl);

        // Request the task list
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8282/PistachoToDo")
                .request().get();
        assertEquals(200, response.getStatus());
        assertEquals(0, response.readEntity(ToDoList.class).getToDoList()
                .size());
    }

    /**
     * Test suite to check that tasks are created correctly on server side
     * @throws IOException
     */
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

        // Create a new task
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

        // Check that the new task exists
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

    /**
     * Test suite to check that all tasks are correctly listed
     * @throws IOException
     */
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

        // Test list of tasks
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

    /**
     * Test suite to check that a particular task can be correctly requested and returned
     * @throws IOException
     */
    @Test
    public void getParticularTask() throws IOException {

        //Prepare server
        ToDoList tl = new ToDoList();
        ToDoTask newTask = new ToDoTask();
        newTask.setTask("TestTask1");
        newTask.setContext("TestContext1");
        newTask.setProject("TestProject1");
        newTask.setPriority(2);
        newTask.setId(1);
        newTask.setHref(URI.create("http://localhost:8282/PistachoToDo/task/1"));
        tl.getToDoList().add(newTask);
        launchServer(tl);

        //Test get task
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8282/PistachoToDo/task/1")
                .request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        ToDoTask newTaskUpdated = response.readEntity(ToDoTask.class);
        assertEquals(newTask.getTask(), newTaskUpdated.getTask());
        assertEquals(newTask.getContext(), newTaskUpdated.getContext());
        assertEquals(newTask.getProject(), newTaskUpdated.getProject());
        assertEquals(newTask.getPriority(), newTaskUpdated.getPriority());
        assertEquals(newTask.getId(), newTaskUpdated.getId());
        assertEquals(newTask.getHref(), newTaskUpdated.getHref());
    }

    /**
     * Test suite to check that a particular task can be correctly deleted on server side
     * @throws IOException
     */
    @Test
    public void deleteParticularTask() throws IOException{

        //Prepare server
        ToDoList tl = new ToDoList();
        ToDoTask newTask = new ToDoTask();
        newTask.setTask("TestTask1");
        newTask.setContext("TestContext1");
        newTask.setProject("TestProject1");
        newTask.setPriority(2);
        newTask.setId(1);
        newTask.setHref(URI.create("http://localhost:8282/PistachoToDo/task/1"));
        tl.getToDoList().add(newTask);
        launchServer(tl);

        //Test delete task
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8282/PistachoToDo/task/1")
                .request(MediaType.APPLICATION_JSON).delete();
        assertEquals(204, response.getStatus());
    }

    /**
     * Method that launches the server
     * @param tl ToDoList list of ToDoTasks
     * @throws IOException
     */
    private void launchServer(ToDoList tl) throws IOException {
        URI uri = UriBuilder.fromUri("http://localhost/").port(8282).build();
        server = GrizzlyHttpServerFactory.createHttpServer(uri,
                new ApplicationConfig(tl));
        server.start();
    }

    /**
     * Method that shuts server down so it can be restarted on another test suite
     */
    @After
    public void shutdown() {
        if (server != null) {
            server.shutdownNow();
        }
        server = null;
    }
}
