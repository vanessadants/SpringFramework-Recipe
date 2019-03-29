package com.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe.service.RecipeService;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    
    @GetMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
    	System.out.println("Getting Index page");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
