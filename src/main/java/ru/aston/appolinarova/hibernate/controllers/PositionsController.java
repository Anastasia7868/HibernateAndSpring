package ru.aston.appolinarova.hibernate.controllers;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aston.appolinarova.hibernate.dao.PositionDaoImpl;
import ru.aston.appolinarova.hibernate.models.Position;

import javax.validation.Valid;

@Controller
@RequestMapping("/positions")
public class PositionsController {

    private final PositionDaoImpl positionDao;

    @Autowired
    public PositionsController(PositionDaoImpl positionDao) {
        this.positionDao = positionDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("positions", positionDao.index());
        return "positions/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("position", positionDao.show(id));
        return "positions/show";
    }

    @GetMapping("/new")
    public String newPosition(@ModelAttribute("position") Position position) {
        return "positions/new";
    }

    @PostMapping
    public String create(@ModelAttribute("position") Position position) {
        positionDao.save(position);
        return "redirect:/positions";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("position", positionDao.show(id));
        return "positions/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Position position, @PathVariable("id") int id) {
        positionDao.update(id, position);
        return "redirect:/positions";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        positionDao.delete(id);
        return "redirect:/positions";
    }

}
