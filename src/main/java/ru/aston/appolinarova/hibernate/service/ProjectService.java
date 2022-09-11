package ru.aston.appolinarova.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.appolinarova.hibernate.dao.ProjectDao;
import ru.aston.appolinarova.hibernate.dto.ProjectDto;
import ru.aston.appolinarova.hibernate.models.Project;

import java.util.List;

@Service
public class ProjectService {

    private ProjectDao projectDao;

    @Autowired
    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public List<Project> showAllProject() {
        return projectDao.showAllProject();
    }

    public Project getById(int id) {
        return projectDao.getById(id);
    }

    public void create(ProjectDto projectDto) {
        projectDao.create(projectDto);
    }

    public void update(int id, Project project) {
        projectDao.update(id, project);
    }

    public void delete(int id) {
        projectDao.delete(id);
    }
}
