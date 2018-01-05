package com.me.repositoryImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.model.Recipe;
import com.me.repository.RecipeRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(value="classpath:root-context.xml")
public class RecipeRepositoryImplIT {

	@Autowired	
	RecipeRepository recipeRepo;
	
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void save_Recie_test() {

		Recipe recipe = new Recipe();
		recipe.setDescription("something");
		
		
		Recipe recipe2 = recipeRepo.save(recipe);
		
		
		assertEquals("something", recipe2.getDescription());
		
	
	}

}
