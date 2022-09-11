package ru.aston.appolinarova.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.appolinarova.hibernate.dao.EmployeeDao;
import ru.aston.appolinarova.hibernate.dto.EmployeeDto;
import ru.aston.appolinarova.hibernate.models.Employee;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> showAllEmployee() {
        return employeeDao.showAllEmployee();
    }

    public Employee getById(int id) {
        return employeeDao.getById(id);
    }

    public void create(EmployeeDto employeeDto) {
        employeeDao.create(employeeDto);
    }

    public void update(int id, Employee employee) {
        employeeDao.update(id, employee);
    }

    public void delete(int id) {
        employeeDao.delete(id);
    }
}
