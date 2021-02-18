package com.green.exampub.controllers;

import com.green.exampub.models.Drink;
import com.green.exampub.models.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DrinksController {

    @Autowired
    private DrinkRepository drinkRepository;


    @GetMapping("/drinks")
    public String drinksMain(Model model){
        Iterable<Drink> drinks = drinkRepository.findAll();
        model.addAttribute("drinks", drinks);
        return "drinks";
}
}
