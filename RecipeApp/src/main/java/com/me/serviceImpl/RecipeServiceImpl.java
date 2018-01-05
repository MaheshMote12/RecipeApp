package com.me.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.command.RecipeCommand;
import com.me.converters.CategoryCommandToCategory;
import com.me.converters.RecipeCommandToRecipe;
import com.me.converters.RecipeToRecipeCommand;
import com.me.exceptions.CategoryNotFountException;
import com.me.exceptions.RecipeNotFoundException;
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
	
	@Autowired
	private CategoryCommandToCategory categoryToCommand;

	
	
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

	@Override
	public List<String> getCategories() {

		  List<String> categories = recipeRepository.findCategories();
		  
		 
		
		if(categories == null || categories.size() < 0 ){
			throw new CategoryNotFountException("Category Not Found ");
		}
		
		return categories;
	}

	@Override
	public RecipeCommand updateRecipe(RecipeCommand recipeCommand) {

		Recipe recipe = recipeRepository.save(toRecipe.convert(recipeCommand));
		
		
		
		return null;
	}

	@Override
	public RecipeCommand findCommandById(Long id) {

		Recipe findById = recipeRepository.findById(id);
		RecipeCommand command = toRecipeCommand.convert(findById);
		
		return command;
	}

	@Override
	public void deleteById(Long id) {
		if(id == null){
			throw new RecipeNotFoundException("No Recipe To Delete");
		}
		recipeRepository.deleteById(id);
	}
	
}
