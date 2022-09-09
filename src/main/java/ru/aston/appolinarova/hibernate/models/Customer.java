package ru.aston.appolinarova.hibernate.models;



import javax.persistence.*;
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

    @OneToMany(mappedBy = "owner")
    private List<Project> projectList;

    public Customer() {
    }

    public Customer(String customerName, String account, List<Project> projectList) {
        this.customerName = customerName;
        this.account = account;
        this.projectList = projectList;
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

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
