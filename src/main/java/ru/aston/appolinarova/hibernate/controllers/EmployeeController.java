package ru.aston.appolinarova.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aston.appolinarova.hibernate.dao.EmployeeDao;
import ru.aston.appolinarova.hibernate.dto.EmployeeDto;
import ru.aston.appolinarova.hibernate.models.Employee;
import ru.aston.appolinarova.hibernate.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employees", employeeService.showAllEmployee());
        return "employees/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employees/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("employee") EmployeeDto employeeDto) {
        return "employees/new";
    }

    @PostMapping
    public String create(@ModelAttribute("employee") EmployeeDto employeeDto) {
        employeeService.create(employeeDto);
        return "redirect:/employees";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employees/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee") Employee employee, @PathVariable("id") int id) {
        employeeService.update(id, employee);
        return "redirect:/employees";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

}
