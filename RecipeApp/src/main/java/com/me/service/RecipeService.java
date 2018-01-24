package com.me.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.me.command.RecipeCommand;
import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;

public interface RecipeService {

	public RecipeCommand saveRecipe(RecipeCommand recipeCommand);
	
	public UnitOfMeasure getUOM(String unit);
	
	public Recipe findRecipeById(Long id);
	
	public List<Recipe> getRecipies();
	
	public List<String> getCategories();

	public RecipeCommand updateRecipe(RecipeCommand recipeCommand);

	public RecipeCommand findCommandById(Long valueOf);
	
	public void deleteById(Long id);

	public void saveImageFile(long recipeId, MultipartFile file);
}
