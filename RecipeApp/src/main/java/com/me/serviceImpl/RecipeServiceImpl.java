package com.me.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;
import com.me.repository.RecipeRepository;
import com.me.service.RecipeService;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{
	
	private RecipeRepository recipeRepository;

	@Autowired
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}
	
	@Override
	public void saveRecipe(Recipe recipe)
	{
		recipeRepository.save(recipe);
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
