package com.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;
import com.me.repository.RecipeRepository;

@Service
public class RecipeService {

	private RecipeRepository recipeRepository;

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}
	
	
	public void saveRecipe(Recipe recipe)
	{
		recipeRepository.save(recipe);
	}
	
	public UnitOfMeasure getUOM(String unit)
	{
		return recipeRepository.getUom(unit);
	}
	
}
