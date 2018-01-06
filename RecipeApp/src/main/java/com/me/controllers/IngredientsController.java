package com.me.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.command.IngrediantsCommand;
import com.me.command.RecipeCommand;
import com.me.command.UnitOfMeasureCommand;
import com.me.service.IngredientService;
import com.me.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IngredientsController {

	private IngredientService ingredientService;
	
	private RecipeService recipeService;

	@Autowired
	public IngredientsController(RecipeService recipeService, IngredientService ingredientService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable("recipeId") Long recipeId, Model model){
		
		log.info("showing ingredents list");
		
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
		
		model.addAttribute("recipe", recipeCommand);
		model.addAttribute("userclickIngredieants", true);
		model.addAttribute("titel", "Ingredients");
		
		return "index";
		
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String ShowIngredient(@PathVariable("recipeId") Long recipeId, @PathVariable("ingredientId") Long ingredientId, Model model){
		
		IngrediantsCommand ingrediantsCommand = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
		model.addAttribute("ingredient", ingrediantsCommand);
		
		return "showIngredient";
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String updateRecipeIngredient(@PathVariable("recipeId") Long recipeId, @PathVariable("ingredientId") Long IngredientId, Model model){
		
		IngrediantsCommand ingrediantsCommand = ingredientService.findByRecipeIdAndIngredientId(recipeId, IngredientId);
		 List<UnitOfMeasureCommand> uoms = ingredientService.listAllUoms();
		
		
		model.addAttribute("ingredient", ingrediantsCommand);
		model.addAttribute("uoms", uoms);
		
		return "ingredientForm";
		
	}
}
