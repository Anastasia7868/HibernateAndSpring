package ru.aston.appolinarova.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.appolinarova.hibernate.dto.EmployeeDto;
import ru.aston.appolinarova.hibernate.dto.ProjectDto;
import ru.aston.appolinarova.hibernate.models.Customer;
import ru.aston.appolinarova.hibernate.models.Employee;
import ru.aston.appolinarova.hibernate.models.Position;
import ru.aston.appolinarova.hibernate.models.Project;

import java.util.List;

@Component
public class ProjectDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ProjectDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Project> showAllProject() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Project getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Project.class, id);
    }

    @Transactional
    public void create(ProjectDto projectDto) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, projectDto.getCustomerId());
        Project project = new Project(projectDto.getProjectName(), projectDto.getCost(), customer);
        customer.getProjectList().add(project);
        session.save(project);

    }

    @Transactional
    public void update(int id, Project project) {
        Session session = sessionFactory.getCurrentSession();
        Project projectToBeUpdated = session.get(Project.class, id);
        projectToBeUpdated.setProjectName(project.getProjectName());
        projectToBeUpdated.setCost(project.getCost());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = session.get(Project.class, id);
        session.remove(project);
        project.getCustomer().getProjectList().remove(project);
    }
}
