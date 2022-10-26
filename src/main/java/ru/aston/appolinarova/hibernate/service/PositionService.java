package ru.aston.appolinarova.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.appolinarova.hibernate.dao.PositionDao;
import ru.aston.appolinarova.hibernate.models.Position;

import java.util.List;

@Service
public class PositionService {

    private final PositionDao positionDao;

    @Autowired
    public PositionService(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public List<Position> findAllPositions() {
        return positionDao.findAllPositions();
    }

    public Position getById(int id) {
        return positionDao.getById(id);
    }

    public void createNewPosition(Position position) {
        positionDao.save(position);
    }

    public void update(int id, Position updatedPosition) {
        positionDao.update(id, updatedPosition);
    }

    public void delete(int id) {
        positionDao.delete(id);
    }
}
