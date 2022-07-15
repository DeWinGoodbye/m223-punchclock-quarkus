package ch.zli.m223.punchclock.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;

/*
 * AuthenticationController
 * The Auth-Controller handles the authentication Process
 * 
 * Source: https://moodle-2.zli.ch/course/view.php?id=1202&section=5
 */

@Path("/auth")
@Tag(name = "Authorization", description = "Handles the User authorization")
public class AuthenticationController {
    @Inject
    AuthenticationService authenticationService; 

    /*
     * Login
     * This HTTP POST-Method handles the Login-request
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(User user){
        if(authenticationService.checkIfUserExists(user)){
            return authenticationService.GenerateValidJwtToken(user.getUsername());
        }
        else{
            throw new NotAuthorizedException("Credentials for the user "+ user.getUsername() +" invalid");
        } 
    }

     /*
     * Register
     * This HTTP POST-Method handles the Register-request
     */
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(@Valid User user) {
        authenticationService.createNewUser(user);
    }
}