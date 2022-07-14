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

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.service.ProjectService;

@Path("/projects")
@Tag(name = "Projects", description = "Handling of projects")
public class ProjectController {
    @Inject
    ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all projects", description = "")
    public List<Project> list() {
        return projectService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Project", description = "The newly created project is returned. The id may not be passed.")
    public Project add(@Valid Project project) {
        return projectService.createProject(project);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a project", description = "The updated project is returned")
    public Project update(@Valid Project project) {
        return projectService.updateProject(project);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deletes a project", description = "The deleted project is returned")
    @Path("/{id}")
    public Project delete(@PathParam("id") long id) {
        return projectService.deleteProject(id);
    }
}