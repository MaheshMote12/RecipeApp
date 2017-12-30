package com.me.command;

import lombok.Getter;
import lombok.Setter;

public class NotesCommand {

	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String notes;

	@Getter @Setter
	private RecipeCommand recipe;

}
