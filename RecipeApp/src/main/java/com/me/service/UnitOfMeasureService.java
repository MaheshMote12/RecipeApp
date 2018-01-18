package com.me.service;

import com.me.command.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	UnitOfMeasureCommand findByUomId(long id);
}
