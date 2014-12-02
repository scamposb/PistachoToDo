package rest;

import pistachotodo.TaskListManager;
import pistachotodo.ToDoList;
import pistachotodo.ToDoTask;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by David Recuenco on 01/11/2014.
 */

@Path("/PistachoToDo")
public class ToDoService {

    @Inject
    ToDoList list;

    /**
     * GET method that returns the task list
     * @return Response with a OK code and a JSON document with the list
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToDoList(){
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    /**
     * POST method that adds a new task to the list
     * @param info info the URI information of the request
     * @param task task to be added
     * @return Response with a CREATED code and the reference to the created item
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToDoTask(@Context UriInfo info, ToDoTask task){
        list.addTask(task);
        task.setId(list.nextId());
        task.setHref(info.getAbsolutePathBuilder().path("/task/{id}").build(task.getId()));
        TaskListManager.saveTaskList(list);
        return Response.created(task.getHref()).entity(task).build();
    }

    /**
     * GET method that returns a particular task from server
     * @param id id of the task to be retrieved
     * @return JSON with the object retrieved
     */
    @GET
    @Path("/task/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(@PathParam("id") int id) {
        for (ToDoTask t : list.getToDoList()) {
            if (t.getId() == id) {
                return Response.ok(t).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * DELETE method that deletes a particular task from server
     * @param id id of the task to be removed
     * @return void JSON
     */
    @DELETE
    @Path("/task/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTask(@PathParam("id") int id) {
        for (int i = 0; i < list.getToDoList().size(); i++){
            if (list.getToDoList().get(i).getId() == id){
                list.getToDoList().remove(i);
                System.out.println("Deleted task.");
                return  Response.noContent().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

}
