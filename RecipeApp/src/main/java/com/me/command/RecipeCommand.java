package com.me.command;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import com.me.model.Difficulty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class RecipeCommand {

	private Long recipeId;
	
	
	private List<IngrediantsCommand> ingrediants = new ArrayList<>();
	
	@Min(1)
	@Max(999)
	private int prepTime;
	@Min(1)
	@Max(999)
	private int cookTime;

	@Min(1)
	@Max(100)
	private int servings;
	
	private String source;
	
	@NotBlank
	@URL
	private String url;
	
//	@Getter @Setter
//	private Set<CategoryCommand> categories = new HashSet<>();
	
	private NotesCommand notes;
	
	@NotBlank
	private String description;
	
	private Difficulty difficulty;
	
	private Byte[] image;
	
	@NotBlank
	private String direction;
	
	private List<CategoryCommand> categories = new ArrayList<>();
}
