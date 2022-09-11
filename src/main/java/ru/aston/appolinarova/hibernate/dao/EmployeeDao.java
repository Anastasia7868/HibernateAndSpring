package ru.aston.appolinarova.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aston.appolinarova.hibernate.dto.EmployeeDto;
import ru.aston.appolinarova.hibernate.models.Employee;

import org.springframework.transaction.annotation.Transactional;
import ru.aston.appolinarova.hibernate.models.Position;

import java.util.List;

@Component
public class EmployeeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public List<Employee> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Employee show(int id) {
        //получаем человека по id
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Transactional
    public void save(EmployeeDto employeeDto) {
        Session session = sessionFactory.getCurrentSession();
        Position position = session.get(Position.class, employeeDto.getPositionId());
        Employee employee = new Employee(employeeDto.getLastName(), employeeDto.getAge(), position);
        position.getEmployeeList().add(employee);
        session.save(employee);

    }

    @Transactional
    public void update(int id, Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        //Position position = session.get(Position.class, employeeDto.getPositionId());
        Employee employeeToBeUpdated = session.get(Employee.class, id);
        employeeToBeUpdated.setLastName(employee.getLastName());
        employeeToBeUpdated.setAge(employee.getAge());
       // employeeToBeUpdated.setPosition(position);
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Employee.class, id));

    }

    private EmployeeDto createDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setAge(employeeDto.getAge());
        employeeDto.setPositionId(employee.getPosition().getId());
        return employeeDto;
    }
}
