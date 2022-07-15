
package ch.zli.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class Startup {
    @Inject
    CategoryService categoryService;

    @Transactional
    public void loadCategories(@Observes StartupEvent evt) {
        addCategory("Desktop Applikation");
        addCategory("Mobile Applikation");
        addCategory("Web Applikation");
        addCategory("Etwas anderes");
    }

    public void addCategory(String name){
        Category category = new Category();
        category.setName(name);
        categoryService.createCategory(category);
    }
}