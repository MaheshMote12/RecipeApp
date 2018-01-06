package com.me.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.command.IngrediantsCommand;
import com.me.command.UnitOfMeasureCommand;
import com.me.converters.IngrediantsToIngrediantsCommand;
import com.me.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.me.model.Ingrediants;
import com.me.model.UnitOfMeasure;
import com.me.repository.IngredientRepository;
import com.me.repository.RecipeRepository;
import com.me.service.IngredientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class IngredientServiceImpl implements IngredientService {

	private RecipeRepository recipeRepo;
	
	private IngredientRepository ingrediantsRepo;
	
	private UnitOfMeasureToUnitOfMeasureCommand toUom;
	
	private IngrediantsToIngrediantsCommand toCommand;
	
	@Autowired
	public IngredientServiceImpl(RecipeRepository recipeRepo, IngredientRepository ingrediantsRepo,
			IngrediantsToIngrediantsCommand toCommand, UnitOfMeasureToUnitOfMeasureCommand toUom) {
		
		super();
		this.recipeRepo = recipeRepo;
		this.ingrediantsRepo = ingrediantsRepo;
		this.toCommand =  toCommand;
		this.toUom = toUom;
	}


	@Override
	public IngrediantsCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Ingrediants ingrediants = ingrediantsRepo.findByRecipeAndIngrediantsId(recipeId, ingredientId);
		
		if(ingrediants == null){
			log.info("Ingrediant is empty");
		}
		
		IngrediantsCommand ingrediantsCommand = toCommand.convert(ingrediants);
		
		
		return ingrediantsCommand;
	}


	@Override
	public List<UnitOfMeasureCommand> listAllUoms() {

		List<UnitOfMeasure> uoms = ingrediantsRepo.finadAllUoms();
		
		List<UnitOfMeasureCommand> listOfuom = new ArrayList<>();
		
		for (UnitOfMeasure unitOfMeasure : uoms) {
			
			UnitOfMeasureCommand command = toUom.convert(unitOfMeasure );
			listOfuom.add(command);
		}

		return listOfuom;
	}

}
