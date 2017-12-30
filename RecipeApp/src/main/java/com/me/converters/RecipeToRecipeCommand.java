package com.me.converters;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.RecipeCommand;
import com.me.model.Category;
import com.me.model.Ingrediants;
import com.me.model.Recipe;

import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	@Autowired
	private NotesToNotesCommand notesConverter;
	
	@Autowired
	private CategoryToCategoryCommand categoryConverter;
	
	@Autowired
	private IngrediantsToIngrediantsCommand ingrediantsCommand;
	
	@Synchronized
	@Override
	public RecipeCommand convert(Recipe recipe) {
		
		if(recipe == null){
			return null;
		}
		
		
		final RecipeCommand command = new RecipeCommand();
		
		command.setCookTime(recipe.getCookTime());
		command.setDescription(recipe.getDescription());
		command.setDifficulty(recipe.getDifficulty());
		command.setDirection(recipe.getDirection());
		command.setImage(recipe.getImage());
		command.setPrepTime(recipe.getPrepTime());
		command.setRecipeId(recipe.getRecipeId());
		command.setServings(recipe.getServings());
		command.setSource(recipe.getSource());
		command.setUrl(recipe.getUrl());

		
		command.setNotes(notesConverter.convert(recipe.getNotes()));
		
		
		Set<Category> categories = recipe.getCategories();
		
		for (Category category : categories) {
			command.getCategories().add( categoryConverter.convert(category) );
		}
		
		
		List<Ingrediants> ingrediants = recipe.getIngrediants();

		for (Ingrediants ingrediants2 : ingrediants) {
			command.getIngrediants().add(ingrediantsCommand.convert(ingrediants2));
		}
		
		
		return command;
	}

}
