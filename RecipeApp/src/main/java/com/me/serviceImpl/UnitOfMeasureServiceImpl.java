package com.me.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.command.UnitOfMeasureCommand;
import com.me.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.me.model.UnitOfMeasure;
import com.me.repository.UnitOfMeasureRepo;
import com.me.service.UnitOfMeasureService;

@Service
@Transactional
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private UnitOfMeasureRepo unitOfMeasureRepo;
	private UnitOfMeasureToUnitOfMeasureCommand uomToCommand;
	
	@Autowired
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepo unitOfMeasureRepo,
			UnitOfMeasureToUnitOfMeasureCommand uomToCommand) {
		super();
		this.unitOfMeasureRepo = unitOfMeasureRepo;
		this.uomToCommand = uomToCommand;
	}


	public UnitOfMeasureServiceImpl(UnitOfMeasureRepo unitOfMeasureRepo) {
		super();
		this.unitOfMeasureRepo = unitOfMeasureRepo;
	}


	@Override
	public UnitOfMeasureCommand findByUomId(long id) {

		UnitOfMeasure uom = unitOfMeasureRepo.findById(id);
		UnitOfMeasureCommand command = uomToCommand.convert(uom);
		
		return command;
	}

}
