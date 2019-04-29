package com.example.food.controllers;

import com.example.food.db.entities.Dish;
import com.example.food.db.repositories.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/dishes")
public class DishController {
    private final DishRepo dishRepo;

    @Autowired
    public DishController(DishRepo dishRepo) {
        this.dishRepo = dishRepo;
    }

    @PostMapping()
    protected String createDish(@RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam(value = "is_vegan") Boolean isVegan,
                                ModelMap m,
                                HttpServletRequest r) {
        Dish dish = Dish.builder()
                .name(name)
                .description(description)
                .isVegan(isVegan)
                .build();
        dishRepo.save(dish);

        return "redirect:/dishes";
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    protected List getDishes() {
        return dishRepo.findAll();
    }
}
