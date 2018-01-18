package com.me.command;

import java.math.BigDecimal;

import javax.persistence.Lob;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class IngrediantsCommand {

	@Getter @Setter
	private Long id;
	
	@Lob
	@Getter @Setter
	private String description;
	
	@Getter @Setter
	private Long recipeId;
	
	@Getter @Setter
	private BigDecimal amount;

	@Getter @Setter
	private UnitOfMeasureCommand uomC;

}
