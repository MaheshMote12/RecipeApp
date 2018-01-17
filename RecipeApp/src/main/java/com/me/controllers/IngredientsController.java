package com.me.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@PostMapping
	@RequestMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdateIngredient(@ModelAttribute("ingredient") IngrediantsCommand ingrediantsCommand, Model model){
		
	/*	System.out.println("INGREDIENTS DESCRIPTION ISl " +ingrediantsCommand.getDescription() );
		System.out.println("INGREDIENTS DESCRIPTION ISl " +ingrediantsCommand.getAmount() );
		System.out.println("UNITS AEEEEEEEEEEEEEEE" +ingrediantsCommand.getUomC().getUnit());
		System.out.println("UOM ID****ddd**************************** " +ingrediantsCommand.getUomC().getId());
*/

		IngrediantsCommand ingredientCommand = ingredientService.saveIngredientCommand(ingrediantsCommand);
		
		log.debug("recipe ID: {} " +ingredientCommand.getRecipeId() );
		log.debug("recipe ID: {} " +ingredientCommand.getId());

		
		return "redirect:/recipe/"+ingredientCommand.getRecipeId()+"/ingredient/"+ingredientCommand.getId()+"/show";
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/new")
	public String addIngredient(@PathVariable("recipeId") Long recipeId, Model model){
		
//		make sure id is valid id
		RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
//		todo handle situation where if id is not valid id
		
		IngrediantsCommand command = new IngrediantsCommand();
		command.setRecipeId(recipeId);
		
		
		
		List<UnitOfMeasureCommand> uoms = ingredientService.listAllUoms();
		model.addAttribute("uoms", uoms);

		model.addAttribute("ingredient", command);
		
		return "ingredientForm";
		
	}
	 
//	@GetMapping
	@RequestMapping(value="/recipe/{recipeId}/ingredient/{ingredientId}/delete", method=RequestMethod.GET)
	public String deletIngredient(@PathVariable("recipeId") Long recipeId, @PathVariable("ingredientId") Long ingrId, Model  model){
		
		ingredientService.deleteByRecipeIdAndIngredientId(recipeId, ingrId);
		
		return "redirect:/recipe/"+recipeId+"/ingredients";
		
		   
	}
	
	
}
