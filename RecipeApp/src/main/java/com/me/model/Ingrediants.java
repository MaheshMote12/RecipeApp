package com.me.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Ingrediants {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Lob
	@Getter @Setter
	private String description;
	
	@ManyToOne(targetEntity=Recipe.class, fetch=FetchType.LAZY)
	@Getter @Setter
	private Recipe recipe;
	
	@Getter @Setter
	private BigDecimal amount;

	@OneToOne
	@Getter @Setter
	private UnitOfMeasure uom;
	
	public Ingrediants() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ingrediants(String description, BigDecimal amount) {
		super();
		this.description = description;
		this.amount = amount;
	}

	public Ingrediants(String description, Recipe recipe, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.recipe = recipe;
		this.amount = amount;
		this.uom = uom;
	}
}
