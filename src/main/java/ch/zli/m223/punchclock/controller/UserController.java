package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;

@Path("/users")
@Tag(name = "Users", description = "Handling of users")
public class UserController {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all users", description = "")
    public List<User> list() {
        return userService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new User", description = "The newly created user is returned. The id may not be passed.")
    public User add(@Valid User user) {
        return userService.createUser(user);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a user", description = "The updated user is returned")
    public User update(@Valid User user) {
        return userService.updateUser(user);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deletes a user", description = "The deleted user is returned")
    @Path("/{id}")
    public User delete(@PathParam("id") long id) {
        return userService.deleteUser(id);
    }
}