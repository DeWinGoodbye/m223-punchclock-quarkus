
package ch.zli.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.CategoryService;
import ch.zli.m223.punchclock.service.ProjectService;
import ch.zli.m223.punchclock.service.UserService;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class Startup {
    @Inject
    CategoryService categoryService;

    @Inject
    ProjectService projectService;

    @Inject
    UserService userService;

    @Transactional
    public void loadCategories(@Observes StartupEvent evt) {
        addCategory("Desktop Applikation");
        addCategory("Mobile Applikation");
        addCategory("Web Applikation");
        addCategory("Etwas anderes");

        addProject("Test Projekt");

        addUser("Testuser", "123", "user");
    }

    public void addCategory(String name){
        Category category = new Category();
        category.setName(name);
        categoryService.createCategory(category);
    }

    public void addProject(String title){
        Project project = new Project();
        project.setTitle(title);
        projectService.createProject(project);
    }

    public void addUser(String username, String password, String role){
        User user = new User();
        user.setUsername(username);
        user.setPassword(username);
        user.setRole(username);

        userService.createUser(user);
    }
}