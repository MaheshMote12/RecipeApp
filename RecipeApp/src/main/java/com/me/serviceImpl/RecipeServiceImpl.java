package com.me.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.command.RecipeCommand;
import com.me.converters.RecipeCommandToRecipe;
import com.me.converters.RecipeToRecipeCommand;
import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;
import com.me.repository.RecipeRepository;
import com.me.service.RecipeService;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{
	
	private RecipeCommandToRecipe toRecipe;
	
	private RecipeToRecipeCommand toRecipeCommand;
	
	@Autowired
	private RecipeRepository recipeRepository;

	
	
	public RecipeServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public RecipeServiceImpl( RecipeRepository recipeRepository, RecipeToRecipeCommand toRecipeCommand, RecipeCommandToRecipe toRecipe) {
		super();
		this.recipeRepository = recipeRepository;
		this.toRecipe = toRecipe;
		this.toRecipeCommand = toRecipeCommand;
	}
	
	@Override
	public RecipeCommand saveRecipe(RecipeCommand recipeCommand)
	{
		Recipe detachedRecipe = toRecipe.convert(recipeCommand);
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		
		return toRecipeCommand.convert(savedRecipe);
		
	}
	
	@Override
	public UnitOfMeasure getUOM(String unit)
	{
		return recipeRepository.getUom(unit);
	}

	@Override
	public Recipe findRecipeById(Long id) {

		Recipe recipe = recipeRepository.findById(id);
		
		if(recipe == null)
		{
			throw new RuntimeException("User Is Not Present");
		}
		return recipe;
		 
	}

	@Override
	public List<Recipe> getRecipies() {
		
		return recipeRepository.getRecipies();
	}
	
}
