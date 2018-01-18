package com.me.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.command.IngrediantsCommand;
import com.me.command.UnitOfMeasureCommand;
import com.me.converters.IngrediantsCommandToIngrediants;
import com.me.converters.IngrediantsToIngrediantsCommand;
import com.me.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.me.model.Ingrediants;
import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;
import com.me.repository.IngredientRepository;
import com.me.repository.RecipeRepository;
import com.me.repository.UnitOfMeasureRepo;
import com.me.service.IngredientService;

import lombok.extern.slf4j.Slf4j;
  
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

	private RecipeRepository recipeRepo;
	private IngredientRepository ingrediantsRepo;
	private IngrediantsCommandToIngrediants ingrCToIngr;
	private IngrediantsToIngrediantsCommand ingrToCommand;
	private UnitOfMeasureRepo uomRepo;
	private UnitOfMeasureToUnitOfMeasureCommand toUom;
	private IngrediantsToIngrediantsCommand toCommand;

	

	@Autowired
	public IngredientServiceImpl(RecipeRepository recipeRepo, IngredientRepository ingrediantsRepo,
			IngrediantsCommandToIngrediants ingrCToIngr, IngrediantsToIngrediantsCommand ingrToCommand,
			UnitOfMeasureRepo uomRepo, UnitOfMeasureToUnitOfMeasureCommand toUom,
			IngrediantsToIngrediantsCommand toCommand) {
		super();
		this.recipeRepo = recipeRepo;
		this.ingrediantsRepo = ingrediantsRepo;
		this.ingrCToIngr = ingrCToIngr;
		this.ingrToCommand = ingrToCommand;
		this.uomRepo = uomRepo;
		this.toUom = toUom;
		this.toCommand = toCommand;
	}


	@Transactional
	@Override
	public IngrediantsCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Ingrediants ingrediants = ingrediantsRepo.findByRecipeAndIngrediantsId(recipeId, ingredientId);
		
		if(ingrediants == null){
			log.info("Ingrediant is empty");
		}
		
		IngrediantsCommand ingrediantsCommand = toCommand.convert(ingrediants);
		
		
		return ingrediantsCommand;
	}

	@Transactional
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


	@Transactional
	@Override
	public IngrediantsCommand saveIngredientCommand(IngrediantsCommand ingrCommand) {

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" +ingrCommand.getUomC().getUnit());
		
		Optional<Recipe> optional = Optional.ofNullable(recipeRepo.findById( ingrCommand.getRecipeId()));
		
		if(!optional.isPresent()){
			log.error("Recipe Not found ");
		}
		else{
				
			Recipe recipe = optional.get();
			log.info("Recipe ID: {} " +recipe.getRecipeId() );
			
			List<Ingrediants> ingrediants = recipe.getIngrediants();
			
			Optional<Ingrediants> ingredientOptional = ingrediants.stream().filter( i ->  i.getId().equals(ingrCommand.getId())).findFirst();
						
			if(ingredientOptional.isPresent()){
				Ingrediants ingrediantFound = ingredientOptional.get();
				ingrediantFound.setAmount( ingrCommand.getAmount() );
				ingrediantFound.setDescription( ingrCommand.getDescription() );
				
				
				ingrediantFound.setUom( uomRepo.findById(ingrCommand.getUomC().getId()));

				System.out.println("UOM *####DddDDD####################################### " +ingrediantFound.getUom().getUnit());
			}
			else{
				Ingrediants convert = ingrCToIngr.convert(ingrCommand);
				convert.setUom( uomRepo.findById(ingrCommand.getUomC().getId() ));
				recipe.addIngredients(  convert);
			}
			
			Recipe savedRecipe = recipeRepo.mergeRecipe(recipe);
					
			Optional<Ingrediants> ingrOptional = savedRecipe.getIngrediants().stream().filter( i -> i.getId().equals(ingrCommand.getId())).findFirst();
			
			
			if(!ingrOptional.isPresent()){
				Ingrediants ingredientFound = savedRecipe.getIngrediants().stream().filter( i -> i.getAmount().equals( ingrCommand.getAmount()))
													  .filter( i -> i.getDescription().equals( ingrCommand.getDescription() ))
													   .filter( i -> i.getUom().getId().equals(ingrCommand.getUomC().getId()) ).findFirst().get();
				return ingrToCommand.convert(ingredientFound);
			}
			
			return ingrToCommand.convert(ingrOptional.get());
		}
		
		return null;
	}

	@Transactional
	@Override
	public void deleteByRecipeIdAndIngredientId(long recipeId, long ingrId) {

		
		ingrediantsRepo.deleteIngredientByRecipeIdIngrId(recipeId, ingrId);
		
	}

}
