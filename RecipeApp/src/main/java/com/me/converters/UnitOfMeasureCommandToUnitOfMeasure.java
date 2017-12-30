package com.me.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.UnitOfMeasureCommand;
import com.me.model.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Synchronized
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand uomc) {

		if(uomc == null){
			return null;
		}

		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(uomc.getId());
		uom.setUnit(uomc.getUnit());
		
		return uom;
	}

}
