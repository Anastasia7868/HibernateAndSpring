package ru.aston.appolinarova.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aston.appolinarova.hibernate.dto.EmployeeDto;
import ru.aston.appolinarova.hibernate.dto.ProjectDto;
import ru.aston.appolinarova.hibernate.models.Employee;
import ru.aston.appolinarova.hibernate.models.Project;
import ru.aston.appolinarova.hibernate.service.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectService projectService;

    @Autowired
    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("projects", projectService.showAllProject());
        return "projects/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "projects/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("project") ProjectDto projectDto) {
        return "projects/new";
    }

    @PostMapping
    public String create(@ModelAttribute("project") ProjectDto projectDto) {
        projectService.create(projectDto);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("project", projectService.getById(id));
        return "projects/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("project") Project project, @PathVariable("id") int id) {
        projectService.update(id, project);
        return "redirect:/projects";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        projectService.delete(id);
        return "redirect:/projects";
    }
}
