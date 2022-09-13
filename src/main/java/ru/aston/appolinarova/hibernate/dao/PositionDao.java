package ru.aston.appolinarova.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.aston.appolinarova.hibernate.models.Position;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PositionDao {

    private SessionFactory sessionFactory;

    @Autowired
    public PositionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Position> findAllPositions() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Position p", Position.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Position getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Position.class, id);
    }

    @Transactional
    public void save(Position position) {
        Session session = sessionFactory.getCurrentSession();
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
        Position position = session.get(Position.class, id);
        session.remove(position);
        position.getEmployeeList().forEach(i -> i.setPosition(null));
    }
}