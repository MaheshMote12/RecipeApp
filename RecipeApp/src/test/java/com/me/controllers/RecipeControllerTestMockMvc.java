package com.me.controllers;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.me.command.RecipeCommand;
import com.me.exceptions.RecipeNotFoundException;
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

	
	@Test
	public void updateRecipeTest() throws Exception{
		
		RecipeCommand recipeCommand = new RecipeCommand();
		
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand );
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
		
		mockMvc.perform(get("/recipe/1/update")).andExpect(status().isOk())
				.andExpect(view().name("index"))
				 .andExpect(model().attributeExists("recipe"));
	}

	
	@Test
	public void deleteRecipeById() throws Exception{
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
		

		mockMvc.perform(get("/recipe/1/delete")).andExpect( status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
		
		verify(recipeService, times(1)).deleteById(anyLong());
	}
	
	@Test
	public void testViewRecipe_If_Not_Present() throws Exception{
		
		when(recipeService.findRecipeById(anyLong())).thenThrow(RecipeNotFoundException.class);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).setControllerAdvice(new ControllerExceptionHandler()).build();
		
		mockMvc.perform( get("/recipe/show/1")).andExpect( status().isNotFound())
				.andExpect( view().name("404error") );
		
		
	}
	
	@Test
	public void testViewRecipe_NumberFormatException() throws Exception{
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).setControllerAdvice(ControllerExceptionHandler.class.newInstance()).build();
		
		mockMvc.perform( get("/recipe/show/sss")).andExpect( status().isBadRequest())
				.andExpect( view().name("400error") );
		
		
	}
	
	@Test
	public void testsaveOrUpdateRecipe() throws Exception{
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
		
		RecipeCommand recipe = new RecipeCommand();
		recipe.setRecipeId(1l);
		
		when(recipeService.saveRecipe(anyObject())).thenReturn(recipe);
		
		mockMvc.perform( post("/recipe/recipeForm")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("description", "descriotf")
				.param("prepTime", "44")
				.param("cookTime", "55")
				.param("servings", "47")
				.param("url", "https://docs.jboss.org")
				.param("direction", "fskdjfls")
				
				
				).andExpect( status().is3xxRedirection() )
		 		.andExpect( view().name("redirect:/recipe/show/1") );
		
	}
	
	@Test
	public void testsaveOrUpdateRecipeFormValidationFail() throws Exception{
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
		
		RecipeCommand recipe = new RecipeCommand();
		recipe.setRecipeId(1l);
		
		
		mockMvc.perform( post("/recipe/recipeForm")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)

				).andExpect( status().isOk() )
		 		.andExpect( view().name("index") );
		
	}
}
