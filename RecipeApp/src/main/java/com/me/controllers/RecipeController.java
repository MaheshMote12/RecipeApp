package com.me.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.model.Recipe;
import com.me.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller 
@Slf4j
@Profile(value={"default", "dev"})  
public class RecipeController 
{
	
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value = {"/", "/index", ""}, method = RequestMethod.GET)
	public String indexPage( Model model) {

		
		log.info("i am in controller class");
		
		List<Recipe> all = recipeService.getRecipies();
		
		model.addAttribute("recipies", all);
		model.addAttribute("titel", "Home");
		model.addAttribute("userclickHome", true);
		
		
		return "index";
	}
	
	@RequestMapping("/recipe/show/{id}")
	public String viewRecipe(@PathVariable("id") String id, Model model){
		
		log.info(" showing recipe detail ");
		
		Recipe recipe = recipeService.findRecipeById(Long.valueOf(id));
		
		model.addAttribute("recipe", recipe);
		model.addAttribute("titel", "Recipe");
		model.addAttribute("userclickRecipe", true);
		
		return "index";
		
	}

}
