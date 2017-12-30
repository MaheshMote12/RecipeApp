package com.me.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.command.NotesCommand;
import com.me.model.Notes;

import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Autowired
	private RecipeToRecipeCommand converter;
	
	@Synchronized
	@Override
	public NotesCommand convert(Notes notes) {
		
		if(notes == null){
			return null;
		}
		
		final NotesCommand command = new NotesCommand();
		command.setId(notes.getId());
		command.setNotes(notes.getNotes());
		
//		this causes stackOverFlow error. do we need link from notes to recipe? 
//		we are going from recipe to notes by default. but the other way aroud we need that to expose in front end?
//		command.setRecipe( converter.convert(notes.getRecipe()) );

		return command;
		
	}

}
