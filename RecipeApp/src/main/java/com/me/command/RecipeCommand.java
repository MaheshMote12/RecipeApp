package com.me.command;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Lob;

import com.me.model.Difficulty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RecipeCommand {

	@Getter @Setter
	private Long recipeId;
	
	@Getter @Setter
	private List<IngrediantsCommand> ingrediants = new ArrayList<>();
	
	@Getter @Setter
	private int prepTime;
	@Getter @Setter
	private int cookTime;
	@Getter @Setter
	private int servings;
	@Getter @Setter
	private String source;
	@Getter @Setter
	private String url;
	
//	@Getter @Setter
//	private Set<CategoryCommand> categories = new HashSet<>();
	
	@Getter @Setter
	private NotesCommand notes;
	
	@Lob
	@Getter @Setter
	private String description;
	
	@Getter @Setter
	private Difficulty difficulty;
	
	@Getter @Setter
	private Byte[] image;
	
	@Getter @Setter
	private String direction;
	
	@Getter @Setter
	List<CategoryCommand> categories = new ArrayList<>();
}
