package com.me.command;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CategoryCommand {

	@Getter @Setter
	private Long categoryId;
	@Getter @Setter
	private String categoryName;
	
	@Getter @Setter
	private Set<RecipeCommand> recipies = new HashSet<>();

}
