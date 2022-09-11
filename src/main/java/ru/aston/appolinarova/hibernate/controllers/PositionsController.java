package ru.aston.appolinarova.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aston.appolinarova.hibernate.dao.PositionDao;
import ru.aston.appolinarova.hibernate.models.Position;
import ru.aston.appolinarova.hibernate.service.PositionService;

@Controller
@RequestMapping("/positions")
public class PositionsController {

    private final PositionService positionService;

    @Autowired
    public PositionsController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("positions", positionService.findAllPositions());
        return "positions/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("position", positionService.getById(id));
        return "positions/show";
    }

    @GetMapping("/new")
    public String newPosition(@ModelAttribute("position") Position position) {
        return "positions/new";
    }

    @PostMapping
    public String create(@ModelAttribute("position") Position position) {
        positionService.createNewPosition(position);
        return "redirect:/positions";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("position", positionService.getById(id));
        return "positions/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Position position, @PathVariable("id") int id) {
        positionService.update(id, position);
        return "redirect:/positions";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        positionService.delete(id);
        return "redirect:/positions";
    }

}
