package com.me.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(exclude={"recipies"})
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long categoryId;
	@Getter @Setter
	private String categoryName;
	
	@Getter @Setter
	@ManyToMany(mappedBy="categories", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, targetEntity=Recipe.class)
	private Set<Recipe> recipies = new HashSet<>();

	
	

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void addRecipe(Recipe recipe)
	{
		recipies.add(recipe);
		recipe.getCategories().add(this);
	}
	

	public void removeRecipe(Recipe  recipe)
	{
		recipies.remove(recipe);
		recipe.getCategories().remove(this);
	}

	
}
