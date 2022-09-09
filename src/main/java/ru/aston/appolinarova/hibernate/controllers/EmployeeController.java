package ru.aston.appolinarova.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aston.appolinarova.hibernate.dao.EmployeeDaoImpl;
import ru.aston.appolinarova.hibernate.models.Employee;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeDaoImpl employeeDao;

    @Autowired
    public EmployeeController(EmployeeDaoImpl employeeDao) {
        this.employeeDao = employeeDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employees", employeeDao.index());
        return "employees/index";
    }

    //метод, который возвращает одного человека по id
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeDao.show(id));
        return "employees/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("employee") Employee employee) {
        return "employees/new";
    }
    @PostMapping
    public String create(@ModelAttribute("employee") Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employees";
    }

}
