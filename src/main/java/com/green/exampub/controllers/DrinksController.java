package com.green.exampub.controllers;

import com.green.exampub.models.Drink;
import com.green.exampub.models.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DrinksController {

    @Autowired
    private DrinkRepository drinkRepository;


    @GetMapping("/drinks")
    public String drinksMain(Model model) {
        Iterable<Drink> drinks = drinkRepository.findAll();
        model.addAttribute("drinks", drinks);
        return "drinksMain";
    }

    @GetMapping("/drinks/add")
    public String drinksAdd(Model model) {
        return "drinksAdd";
    }

    @PostMapping("/drinks/add")
    public String drinksDrinkAdd(@RequestParam String productName, @RequestParam int price, @RequestParam(defaultValue = "false") boolean isForAdult, Model model) {
        Drink drink = new Drink(productName, price, isForAdult);
        drinkRepository.save(drink);
        return "redirect:/drinks";
    }

    @GetMapping("/drinks/{id}")
    public String drinksDetails(@PathVariable(value = "id") long drinkId, Model model) {
        if (!drinkRepository.existsById(drinkId)) {
            return "redirect:/drinks";
        }
        Optional<Drink> drink = drinkRepository.findById(drinkId);
        ArrayList<Drink> res = new ArrayList<>();
        drink.ifPresent(res::add);
        model.addAttribute("drink", res);
        return "drinksDetails";
    }

    @GetMapping("/drinks/{id}/edit")
    public String drinksEdit(@PathVariable(value = "id") long drinkId, Model model) {
        if (!drinkRepository.existsById(drinkId)) {
            return "redirect:/drinks";
        }
        Optional<Drink> drink = drinkRepository.findById(drinkId);
        ArrayList<Drink> res = new ArrayList<>();
        drink.ifPresent(res::add);
        model.addAttribute("drink", res);
        return "drinksEdit";
    }
    @PostMapping("/drinks/{id}/edit")
    public String drinksDrinkUpdate(@PathVariable(value = "id") long drinkId, @RequestParam String productName, @RequestParam int price, @RequestParam(defaultValue = "false") boolean isForAdult, Model model) {
        Drink drink = drinkRepository.findById(drinkId).orElseThrow();
        drink.setProductName(productName);
        drink.setPrice(price);
        drink.setForAdult(isForAdult);
        drinkRepository.save(drink);
        return "redirect:/drinks";
    }
    @PostMapping("/drinks/{id}/remove")
    public String drinksDrinkDelete(@PathVariable(value = "id") long drinkId, Model model) {
        Drink drink = drinkRepository.findById(drinkId).orElseThrow();
        drinkRepository.delete(drink);
        return "redirect:/drinks";
    }
}