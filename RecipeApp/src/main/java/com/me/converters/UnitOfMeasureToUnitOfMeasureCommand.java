package com.me.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.UnitOfMeasureCommand;
import com.me.model.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Synchronized
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure uom) {

		if(uom == null){
			return null;
		}
		
		final UnitOfMeasureCommand uomC = new UnitOfMeasureCommand();
		
		uomC.setId(uom.getId());
		uomC.setUnit(uom.getUnit());
		
		return uomC;
	}

}
