package com.me.command;

import java.math.BigDecimal;

import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

public class IngrediantsCommand {

	@Getter @Setter
	private Long id;
	
	@Lob
	@Getter @Setter
	private String description;
	
	@Getter @Setter
	private RecipeCommand recipeCommand;
	
	@Getter @Setter
	private BigDecimal amount;

	@Getter @Setter
	private UnitOfMeasureCommand uomC;

}
