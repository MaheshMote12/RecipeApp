package com.me.service;

import java.util.List;

import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;

public interface RecipeService {

	public void saveRecipe(Recipe recipe);
	
	public UnitOfMeasure getUOM(String unit);
	
	public Recipe findRecipeById(Long id);
	
	public List<Recipe> getRecipies();
}
