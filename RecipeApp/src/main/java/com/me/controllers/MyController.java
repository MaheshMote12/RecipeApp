package com.me.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.model.Recipe;
import com.me.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@Profile(value={"default", "dev"})
public class MyController 
{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String home( Model model) {

		log.info("i am in controller class");
		
		List<Recipe> all = recipeRepo.getAllRecipies();
		
		model.addAttribute("recipies", all);
		
		
		return "home";
	}

}
