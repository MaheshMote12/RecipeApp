package com.me.repository;

import com.me.model.UnitOfMeasure;

public interface UnitOfMeasureRepo {

	UnitOfMeasure findById(long id);
	UnitOfMeasure findByUnit(String unit);
	
	
}
