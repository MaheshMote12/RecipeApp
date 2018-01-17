package com.me.repository;

import java.util.List;

import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;

public interface RecipeRepository {

	Recipe save(Recipe recipe);
	List<Recipe> getRecipies();
	UnitOfMeasure getUom(String uom);
	Recipe findById(Long id);
	public List<String> findCategories();
	void deleteById(Long l);
	Recipe mergeRecipe(Recipe recipe);
}
