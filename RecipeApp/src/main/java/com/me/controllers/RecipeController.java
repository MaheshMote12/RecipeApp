package com.me.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.me.command.RecipeCommand;
import com.me.exceptions.RecipeNotFoundException;
import com.me.model.Difficulty;
import com.me.model.Recipe;
import com.me.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller 
@Slf4j
@Profile(value={"default", "dev"})  
public class RecipeController 
{
	
	private  static final  String RECIPE_RECIPE_URL = "recipeForm";
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value = {"/", "/index", ""}, method = RequestMethod.GET)
	public String indexPage( Model model) {

		System.out.println("##################################################");
		
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
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value=RecipeNotFoundException.class)
	public ModelAndView handleNotFound(Exception exception){
		log.info("ExceptionHandler error method ");
		log.error(exception.getMessage() );
		
		ModelAndView view = new ModelAndView("404error");
		
		view.addObject("exception", exception);
		return view;
	}

	
	
	@GetMapping
	@RequestMapping("/recipe/new")
	public String saveRecipe(Model model){
		
//		RecipeCommand command = new RecipeCommand();
		
		model.addAttribute("diff", Difficulty.values());
		
/*		Set<Category> l = new HashSet<>();
		
		for (Recipe recipe : recipies) {
		System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR " +recipe.getCategories().size());	
			l.addAll(recipe.getCategories());
		}
		
		   
		
		
		model.addAttribute("cate", l);
*/		
		
//		model.addAttribute("cate", recipeService.getCategories());
		
		
		model.addAttribute("recipe", new RecipeCommand());
		model.addAttribute("titel", "New Recipe");
		model.addAttribute("userclickNewRecipe", true);

		return "index";
	}

	@PostMapping("/recipe/recipeForm")
	/*@RequestMapping(value="/recipe", method=RequestMethod.POST)*/
	public String saveOrUpdateRecipe( @Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult result, Model model){
		
//		uncomment to get back to Set instead of List
//		Set<CategoryCommand> set = recipeCommand.getCategories();
		
		
		if(result.hasErrors()){
			
			result.getAllErrors().forEach( errorObj -> log.error(errorObj.toString()) );
			
			model.addAttribute("titel", "New Recipe");
			model.addAttribute("userclickNewRecipe", true);
			
			return "index";
		}
		
		
	
		
		RecipeCommand savedRecipe = recipeService.saveRecipe(recipeCommand);
		
		model.addAttribute("titel", "Recipe");
		model.addAttribute("userclickRecipe", true);

		
		return "redirect:/recipe/show/"+savedRecipe.getRecipeId();
	}

	@GetMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable("id") Long id, Model model){
		
		 RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
		 
		 System.out.println(".>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		model.addAttribute("recipe", recipeCommand);
		
		System.out.println("************************************* " +recipeCommand.getCookTime());
		
		model.addAttribute("titel", "New Recipe");
		model.addAttribute("userclickNewRecipe", true);
		
		
		return "index";
	}
	
	@GetMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable("id") Long id){
		
		recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/";
	}
	

}
