package com.me.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.model.Recipe;
import com.me.repository.RecipeRepository;
import com.me.service.RecipeService;
import com.me.serviceImpl.RecipeServiceImpl;

@RunWith(PowerMockRunner.class)
public class RecipeServiceImplTest {

	@Mock
	private RecipeRepository recipeRepo;
	
	private RecipeService recipeService; 
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepo);
	
	}

	/*@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveRecipe() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUOM() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testFindRecipeById_If_Present() {
		
		Recipe recipe = new Recipe();
		recipe.setRecipeId(1l);
		
		when(recipeRepo.findById(anyLong())).thenReturn(recipe);
		
		
		Recipe recipe2 = recipeService.findRecipeById(1l);
		
		assertNotNull("null recipe returned", recipe2);
		assertThat(1l, is(recipe2.getRecipeId()));
		
		verify(recipeRepo, times(1)).findById(anyLong());
		verify(recipeRepo, never()).getRecipies();
		
		
		
		
	}
/*
	@Test
	public void testGetRecipies() {
		fail("Not yet implemented");
	}
*/
}
