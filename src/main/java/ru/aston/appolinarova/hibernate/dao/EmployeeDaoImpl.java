package ru.aston.appolinarova.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.appolinarova.hibernate.models.Employee;
import ru.aston.appolinarova.hibernate.models.Position;

import java.util.List;

@Component
public class EmployeeDaoImpl {

    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public List<Employee> index() {
        Session session = sessionFactory.getCurrentSession();
        // Здесь обычный Hibernate код
        return session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Employee show(int id) {
        //получаем человека по id
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Transactional
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }
}
