package com.me.controllers;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.me.model.Recipe;
import com.me.service.RecipeService;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTestMockMvc {

	
	@Mock
	private RecipeService recipeService;
	

	@InjectMocks
	private RecipeController  myController;
	
	@Test
	public void indexPageTest() throws Exception  {
		
//		given
		List<Recipe> list = Arrays.asList(new Recipe(), new Recipe() );
		
		when(recipeService.getRecipies()).thenReturn(list);
		
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));

		
	}
	
	@Test
	public void viewRecipeTest() throws Exception{
		
		Recipe recipe = new Recipe();
		recipe.setRecipeId(1l);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();

		when(recipeService.findRecipeById(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk())
				.andExpect(view().name("index"))
				 .andExpect(model().attributeExists("titel","recipe", "userclickRecipe"));
		
		
	}
	
	
}
