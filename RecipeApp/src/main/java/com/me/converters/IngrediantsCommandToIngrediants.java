package com.me.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.IngrediantsCommand;
import com.me.model.Ingrediants;

import lombok.Synchronized;

@Component
public class IngrediantsCommandToIngrediants implements Converter<IngrediantsCommand, Ingrediants> {

	@Autowired
	private UnitOfMeasureCommandToUnitOfMeasure uom;
	
	
	@Synchronized
	@Override
	public Ingrediants convert(IngrediantsCommand command) {

		if(command == null){
			return null;
		}
		
		final Ingrediants ingrediants = new Ingrediants();
		ingrediants.setId(command.getId());
		ingrediants.setAmount(command.getAmount());
		ingrediants.setDescription(command.getDescription());
		ingrediants.setId(command.getId());
		ingrediants.setUom(uom.convert(command.getUomC()) );
	
		return ingrediants;
	}
}
