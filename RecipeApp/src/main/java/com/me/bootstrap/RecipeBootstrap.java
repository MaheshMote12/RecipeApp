package com.me.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.me.model.Category;
import com.me.model.Difficulty;
import com.me.model.Ingrediants;
import com.me.model.Notes;
import com.me.model.Recipe;
import com.me.repository.RecipeRepository;
import com.me.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private RecipeService recipeService;
	
	@Autowired
	private RecipeRepository repo;
	
	@Autowired
	public RecipeBootstrap(RecipeService recipeService) {
		super();
		log.debug("application event started");
		this.recipeService = recipeService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
/*		recipeService.saveRecipe(setup());
		recipeService.saveRecipe(setup());
*/	
		repo.save(setup());
		repo.save(setup());

		System.out.println(repo.getRecipies().get(0).getCategories().size() );
		
	}

	private Recipe setup() {

		Recipe recipe = new Recipe();

		Ingrediants ingrd = new Ingrediants();
		ingrd.setAmount(new BigDecimal(55));
		ingrd.setDescription("evey single ingrediant brings delicious taste in the food ");
		ingrd.setUom(recipeService.getUOM("scoop"));
		
		Ingrediants ingrd2 = new Ingrediants();
		ingrd2.setAmount(new BigDecimal(75));
		ingrd2.setDescription("all the ingrediaents in this recipe are very healthy ");
		ingrd2.setUom(recipeService.getUOM("tea spoon"));
		
		Notes note1 = new Notes();
		note1.setNotes("very tasty");
		note1.setRecipe(recipe);
		
		Category category1 = new Category();
		category1.setCategoryName("Sweet Food");
//		no need to add from here we added it already via helper method
//		category1.setRecipies(Arrays.asList(recipe));
		category1.addRecipe(recipe);
		
		
		Category category2 = new Category();
		category2.setCategoryName("Delicious Food");
		category2.addRecipe(recipe);
		
		
		
		recipe.setDescription("pancake made recipe");
		
		
		recipe.addIngredients(ingrd);
		recipe.addIngredients(ingrd2);
		recipe.setCookTime(1);
		recipe.setDifficulty(Difficulty.EAST);
		recipe.setNotes(note1);
		recipe.setPrepTime(2);
		recipe.setServings(4);
		recipe.setUrl("http://localhost:8080/g/");
		recipe.setSource("http://www.google.com");
		recipe.setDirection("first, you gotta wash those veggies! second, you must have to be 100% Vegan!!");

		
//		no need to add from here we added it already via helper method
//		recipe.setCategories(Arrays.asList(category2, category1));
		
		
		recipe.addCategory(category2);
		recipe.addCategory(category1);
		
//		no need to add from here we added it already via helper method
//		recipe.getCategories().add(category2);
//		category2.getRecipies().add(recipe);
		
		
		
		
		
		
		
		return recipe;
		
		
	}

}
