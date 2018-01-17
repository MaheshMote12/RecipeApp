package com.me.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Ingrediants {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Lob
	@Getter @Setter
	private String description;
	
	@ManyToOne(targetEntity=Recipe.class, fetch=FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@Getter @Setter
	private Recipe recipe;
	
	@Getter @Setter
	private BigDecimal amount;

	/*@OneToOne(cascade={CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH,
			CascadeType.MERGE}, fetch=FetchType.EAGER)*/
	@OneToOne(cascade={CascadeType.ALL})
	@OnDelete(action=OnDeleteAction.CASCADE)
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
	
	public void removeRecipe(){
		this.recipe = null;
	}
	public void removeUnit(){
		this.uom = null;
	}
	
	
}
