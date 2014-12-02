package rest;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import pistachotodo.ToDoList;

/**
 * Created by David Recuenco on 01/11/2014.
 */
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        this(new ToDoList());
    }

    public ApplicationConfig(final ToDoList toDoList) {
        register(CrossDomainFilter.class);
        register(ToDoService.class);
        register(MOXyJsonProvider.class);
        register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(toDoList).to(ToDoList.class);
            }});
    }


}
