package com.me.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.UnitOfMeasureCommand;
import com.me.model.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
