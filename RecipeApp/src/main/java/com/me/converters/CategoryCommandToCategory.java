package com.me.converters;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.CategoryCommand;
import com.me.command.RecipeCommand;
import com.me.model.Category;

import lombok.Synchronized;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

	@Autowired
	private RecipeCommandToRecipe recipeConverter;
	
	@Synchronized
	@Override
	public Category convert(CategoryCommand command) {

		if(command == null){
			return null;
		}
		
		final Category category = new Category();
		
		category.setCategoryId(command.getCategoryId());
		category.setCategoryName(command.getCategoryName());
//		category.getRecipies().addAll(command.getRecipies());
		
		 Set<RecipeCommand> recipies = command.getRecipies();
		
		for (RecipeCommand recipeCommand : recipies) {
			category.addRecipe(recipeConverter.convert(recipeCommand));
		}
		
		
		return category;
	}

}
