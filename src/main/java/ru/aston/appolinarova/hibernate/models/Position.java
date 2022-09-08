package ru.aston.appolinarova.hibernate.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "position")
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
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
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

    public void addEmployee(Employee employee){
        if (this.employees == null){
            this.employees = new ArrayList<>();
        }
        this.employees.add(employee);
        employee.setPosition(this);
    }
}
