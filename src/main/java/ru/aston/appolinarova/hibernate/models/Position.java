package ru.aston.appolinarova.hibernate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Position")
public class Position {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "department")
    private String department;

    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

    public Position() {
    }

    public Position(String positionName, String department) {
        this.positionName = positionName;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
