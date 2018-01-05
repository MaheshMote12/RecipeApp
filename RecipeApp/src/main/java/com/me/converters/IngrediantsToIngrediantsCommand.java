package com.me.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.IngrediantsCommand;
import com.me.model.Ingrediants;

@Component
public class IngrediantsToIngrediantsCommand  implements Converter<Ingrediants,IngrediantsCommand>{

	@Autowired
	private RecipeToRecipeCommand recipeConverter;
	
	@Autowired
	private UnitOfMeasureToUnitOfMeasureCommand uomConverter;
	
	@Override
	public IngrediantsCommand convert(Ingrediants ingrediants) {
		
		if(ingrediants== null){
			return null;
		}
		
		final IngrediantsCommand command = new IngrediantsCommand();
	
		command.setId(ingrediants.getId());
		command.setAmount(ingrediants.getAmount());
		command.setDescription(ingrediants.getDescription());
		command.setId(ingrediants.getId());
		

//		no need of travelling from ingredients to recipe for front end, right?
/*		command.setRecipeCommand(  recipeConverter.convert(ingrediants.getRecipe()) );
*/		
		command.setUomC(uomConverter.convert(ingrediants.getUom()));
		
		return command;
	}

}
