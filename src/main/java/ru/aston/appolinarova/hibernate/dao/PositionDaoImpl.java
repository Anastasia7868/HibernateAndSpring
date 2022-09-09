package ru.aston.appolinarova.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.appolinarova.hibernate.models.Customer;
import ru.aston.appolinarova.hibernate.models.Position;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PositionDaoImpl {

    private SessionFactory sessionFactory;

    @Autowired
    public PositionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Position> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Position p", Position.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Position show(int id) {
        //получаем человека по id
        Session session = sessionFactory.getCurrentSession();
        return session.get(Position.class, id);
    }

    @Transactional
    public void save(Position position) {
        Session session = sessionFactory.getCurrentSession();
        position.setEmployeeList(new ArrayList<>(Collections.emptyList()));
        session.save(position);
    }

    @Transactional
    public void update(int id, Position updatedPosition) {
        Session session = sessionFactory.getCurrentSession();

        Position positionToBeUpdated = session.get(Position.class, id);
        positionToBeUpdated.setPositionName(updatedPosition.getPositionName());
        positionToBeUpdated.setDepartment(updatedPosition.getDepartment());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Position.class, id));

    }
}
