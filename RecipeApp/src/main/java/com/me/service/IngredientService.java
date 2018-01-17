package com.me.service;

import java.util.List;

import com.me.command.IngrediantsCommand;
import com.me.command.UnitOfMeasureCommand;

public interface IngredientService {

	IngrediantsCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	List<UnitOfMeasureCommand> listAllUoms();

	IngrediantsCommand saveIngredientCommand(IngrediantsCommand ingrCommand);

	void deleteByRecipeIdAndIngredientId(long recipeId, long ingrId);

	
	
}
