package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.example.model.User;
import com.example.controller.UserController;
import com.example.controller.UserControllerFactory;

/**
 * Root resource (exposed at "users" path)
 */
@Path("users")
public class UserEndpoint {
    @GET
    @Path("{version}/userid/{userid}")
    @Produces("application/xml")
    public User getUsingId(@PathParam("version") String version, @PathParam("userid") String userid) {
        UserController controller = UserControllerFactory.getController(version);
	return controller.process(userid);
    }
}
