package ru.aston.appolinarova.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.appolinarova.hibernate.models.Customer;

import java.util.List;

@Component
public class CustomerDao {
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Customer> findAllCustomers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Customer getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Transactional
    public void create(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);
    }

    @Transactional
    public void update(int id, Customer updatedCustomer) {
        Session session = sessionFactory.getCurrentSession();

        Customer customerToBeUpdated = session.get(Customer.class, id);
        customerToBeUpdated.setName(updatedCustomer.getName());
        customerToBeUpdated.setAccount(updatedCustomer.getAccount());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        session.remove(customer);
        customer.getProjectList().forEach(i -> i.setCustomer(null));
    }
}
