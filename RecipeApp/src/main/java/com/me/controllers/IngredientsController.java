package com.me.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.command.RecipeCommand;
import com.me.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IngredientsController {

	private RecipeService recipeService;

	@Autowired
	public IngredientsController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String viewIngredients(@PathVariable("recipeId") Long recipeId, Model model){
		
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
		
		model.addAttribute("recipe", recipeCommand);
		model.addAttribute("userclickIngredieants", true);
		model.addAttribute("titel", "Ingredients");
		
		
		return "index";
		
		
	}
	
	
	
}
