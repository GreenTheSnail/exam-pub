package com.green.exampub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DrinksController {

@GetMapping("/drinks")
public String drinksMain(Model model){
    model.addAttribute("title", "Drink menu");
    return "drinks";
}
}
