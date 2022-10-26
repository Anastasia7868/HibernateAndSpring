package ru.aston.appolinarova.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.appolinarova.hibernate.dao.CustomerDao;
import ru.aston.appolinarova.hibernate.models.Customer;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> findAllCustomers() {
        return customerDao.findAllCustomers();
    }

    public Customer getById(int id) {
        return customerDao.getById(id);
    }

    public void create(Customer customer) {
        customerDao.create(customer);
    }

    public void update(int id, Customer updatedCustomer) {
        customerDao.update(id, updatedCustomer);
    }

    public void delete(int id) {
        customerDao.delete(id);
    }
}
