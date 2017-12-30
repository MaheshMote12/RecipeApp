package com.me.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.CategoryCommand;
import com.me.model.Category;

import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Autowired
	private RecipeToRecipeCommand recipeConverter;
	
	@Synchronized
	@Override
	public CategoryCommand convert(Category category) {

		if(category == null){
			return null;
		}
	
		final CategoryCommand command = new CategoryCommand();
		command.setCategoryId(category.getCategoryId());
		command.setCategoryName(category.getCategoryName());

		
//		do we need this deadlock? do we need to travel from category to recipe in front end? 
//		i think we need this feature in DB context only. but here?
/*		Set<Recipe> recipies = category.getRecipies();
		Set<RecipeCommand> rc = new HashSet<>();
		
		for (Recipe recipe : recipies) {

			RecipeCommand recipeCommand = recipeConverter.convert(recipe);
			rc.add(recipeCommand);
		}
		
		command.setRecipies(rc);
*/		
		return command;
	}

}
