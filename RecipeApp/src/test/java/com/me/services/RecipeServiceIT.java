package com.me.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.me.command.RecipeCommand;
import com.me.converters.RecipeToRecipeCommand;
import com.me.model.Recipe;
import com.me.repository.RecipeRepository;
import com.me.service.RecipeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class RecipeServiceIT {

	
	private final static String NEW_DESCRIPTION = "new description";
	
	@Autowired
	RecipeService recipeService;
	@Autowired
	RecipeRepository recipeRepo;

	@Autowired
	RecipeToRecipeCommand recipeToCommand;

	@Before
	public void setUp() throws Exception {
	}

/*	@Test
	public void testRecipeServiceImpl() {
		fail("Not yet implemented");
	}
*/
	/*@Transactional*/
	@Test
	public void testSaveRecipe() {
		
//		given
		List<Recipe> recipies = recipeRepo.getRecipies();
		Recipe recipe = recipies.get(0);
		RecipeCommand recipeCommand = recipeToCommand.convert(recipe);
		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEErrrrrrrrrrrrrrrrr ");

		
		
//		when
		recipeCommand.setDescription(NEW_DESCRIPTION);
		RecipeCommand saveRecipeCommand = recipeService.saveRecipe(recipeCommand);

//		then
		
		assertEquals(NEW_DESCRIPTION, saveRecipeCommand.getDescription());
		assertEquals(recipeCommand.getCategories().size(), saveRecipeCommand.getCategories().size());
		assertEquals(recipeCommand.getIngrediants().size(),saveRecipeCommand.getIngrediants().size());
		
		
	}

	
	@Test
	public void testGetUOM() {
	
		recipeService.getCategories();
	
	}
	
	
/*	@Test
	public void testGetUOM() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindRecipeById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRecipies() {
		fail("Not yet implemented");
	}
*/
}
