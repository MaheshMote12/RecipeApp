package com.me.repository;

import java.util.List;

 
import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;

public interface RecipeRepository {

	void save(Recipe recipe);
	List<Recipe> getAllRecipies();
	UnitOfMeasure getUom(String uom) ;
}
