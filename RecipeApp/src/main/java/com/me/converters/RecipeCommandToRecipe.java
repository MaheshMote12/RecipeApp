package com.me.converters;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.CategoryCommand;
import com.me.command.IngrediantsCommand;
import com.me.command.RecipeCommand;
import com.me.model.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	@Autowired
	private NotesCommandToNotes notesConverter;
	@Autowired
	private CategoryCommandToCategory categoryConverter;
	@Autowired
	private IngrediantsCommandToIngrediants ingrediantsConverter;
	
	
	@Synchronized
	@Override
	public Recipe convert(RecipeCommand command) {

		if(notesConverter == null){
			System.out.println("notes converter is null");
		}
		
		if(command == null){
			return null;
		}
		
		
		final Recipe recipe = new Recipe();
		
		recipe.setCookTime(command.getCookTime());
		
		
		recipe.setDescription(command.getDescription());
		recipe.setDifficulty(command.getDifficulty());
		recipe.setDirection(command.getDirection());
		recipe.setImage(command.getImage());
		
		recipe.setNotes( notesConverter.convert(command.getNotes()));
		
		recipe.setPrepTime(command.getPrepTime());
		recipe.setRecipeId(command.getRecipeId());
		recipe.setServings(command.getServings());
		recipe.setSource(command.getSource());
		recipe.setUrl(command.getUrl());
		
		Set<CategoryCommand> categories = command.getCategories();
		
		for (CategoryCommand categoryCommand : categories) {
//			recipe.getCategories().add( categoryConverter.convert(categoryCommand) );
			
			recipe.addCategory(categoryConverter.convert(categoryCommand)   );
		}
		
		List<IngrediantsCommand> ingrediants = command.getIngrediants();
		
		for (IngrediantsCommand ingrediantsCommand : ingrediants) {
			
			recipe.addIngredients(ingrediantsConverter.convert(ingrediantsCommand));
		}
		
		
		return recipe;
	}

}
