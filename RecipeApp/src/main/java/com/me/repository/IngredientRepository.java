package com.me.repository;

import java.util.List;

import com.me.model.Ingrediants;
import com.me.model.UnitOfMeasure;

public interface IngredientRepository {



	Ingrediants findByRecipeAndIngrediantsId(Long recipeId, Long ingrediantsId);

	List<UnitOfMeasure> finadAllUoms();

	void deleteIngredientByRecipeIdIngrId(long recipeId, long ingrId);

}
