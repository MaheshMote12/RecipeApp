package com.me.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(exclude={"ingrediants", "categories", "notes"})
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private Long recipeId;
	
	@Getter @Setter
	@OneToMany(cascade=CascadeType.ALL, /*fetch=FetchType.EAGER,*/ mappedBy="recipe")
	private List<Ingrediants> ingrediants = new ArrayList<>();
	
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
	
	@Getter @Setter
	@ManyToMany(cascade=CascadeType.ALL, targetEntity=Category.class)
	@JoinTable(name="recipe_category", 
			   joinColumns=@JoinColumn(name="recipe_fk", referencedColumnName="recipeId", nullable=false), 
			   inverseJoinColumns=@JoinColumn(name="category_fk", referencedColumnName="categoryId"))
	private Set<Category> categories = new HashSet<>();
	
	@Getter @Setter
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, targetEntity=Notes.class)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Notes notes;
	
	@Lob
	@Getter @Setter
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Difficulty difficulty;
	
	@Lob
	@Getter @Setter
	private byte[] image;
	
	
	
/**	these two utility methods are very usefull and efficient. always use this when dealing with onetomany
 * **/	
	public void addIngredients(Ingrediants ingredient)
	{
		ingrediants.add(ingredient);
		ingredient.setRecipe(this);
	}
	

	public void removeIngredients(Ingrediants ingredient)
	{
		ingrediants.remove(ingredient);
		ingredient.setRecipe(null);
	}
	
	
	public void addCategory(Category category)
	{
		categories.add(category);
		category.getRecipies().add(this);
	}
	

	public void removeCategory(Category category)
	{
		categories.remove(category);
		category.getRecipies().remove(this);
	}




}
