package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Project;

@ApplicationScoped
public class ProjectService {
    @Inject
    private EntityManager entityManager;

    public ProjectService() {
    }

    @Transactional 
    public Project createProject(Project project) {
        entityManager.merge(project);
        return project;
    }

    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        var query = entityManager.createQuery("FROM Project");
        return query.getResultList();
    }

    @Transactional
    public Project updateProject(Project project) {
        entityManager.merge(project);
        return project;
    }

    @Transactional
    public Project deleteProject(long id) {
        Project project = entityManager.find(Project.class, id);
        entityManager.remove(project);
        return project;
    }
}