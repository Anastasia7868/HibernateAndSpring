package ru.aston.appolinarova.hibernate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "cost")
    private int cost;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    public Project() {
    }

    public Project(String projectName, int cost, Customer customer) {
        this.projectName = projectName;
        this.cost = cost;
        this.customer = customer;
    }

    public Project(String projectName, int cost) {
        this.projectName = projectName;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (cost != project.cost) return false;
        return projectName != null ? projectName.equals(project.projectName) : project.projectName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }
}
