package com.me.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.NotesCommand;
import com.me.model.Notes;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	@Autowired
	private RecipeCommandToRecipe recipeConverter;
	
	@Synchronized
	@Override
	public Notes convert(NotesCommand command) {

		if(command == null){
			return null;
		}
		
		final Notes notes = new Notes();
		notes.setId(command.getId());
		notes.setNotes(command.getNotes());
		
		notes.setRecipe(recipeConverter.convert(command.getRecipe()));
		
		return notes;
	
	}

}
