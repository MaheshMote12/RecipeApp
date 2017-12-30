package com.me.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.IngrediantsCommand;
import com.me.model.Ingrediants;

import lombok.Synchronized;

@Component
public class IngrediantsCommandToIngrediants implements Converter<IngrediantsCommand, Ingrediants> {

	@Synchronized
	@Override
	public Ingrediants convert(IngrediantsCommand command) {

		if(command == null){
			return null;
		}
		
		final Ingrediants ingrediants = new Ingrediants();
		ingrediants.setAmount(command.getAmount());
		ingrediants.setDescription(command.getDescription());
		ingrediants.setId(ingrediants.getId());
		ingrediants.setRecipe(ingrediants.getRecipe() );
		ingrediants.setUom(ingrediants.getUom() );
	
		return ingrediants;
	}
}
