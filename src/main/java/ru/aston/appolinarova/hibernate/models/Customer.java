package ru.aston.appolinarova.hibernate.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "account")
    private String account;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "customer")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Project> projects;

    public Customer() {
    }

    public Customer(String customerName, String account, String email) {
        this.customerName = customerName;
        this.account = account;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        if (this.projects == null){
            this.projects = new ArrayList<>();
        }
        this.projects.add(project);
        project.setCustomer(this);
    }
}
