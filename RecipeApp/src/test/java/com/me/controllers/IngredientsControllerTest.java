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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.me.command.IngrediantsCommand;
import com.me.command.RecipeCommand;
import com.me.command.UnitOfMeasureCommand;
import com.me.service.IngredientService;
import com.me.service.RecipeService;

@RunWith(PowerMockRunner.class)
public class IngredientsControllerTest {

	@Mock
	private IngredientService ingredientService;
	
	@Mock
	private RecipeService recipeService;
	
	private IngredientsController controller;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
		
		controller = new IngredientsController(recipeService, ingredientService);
	}
	
	
	@Test
	public void testListIngredients() throws Exception{
		
		RecipeCommand command = new RecipeCommand();
		
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(get("/recipe/1/ingredients")).andExpect(view().name("index"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("recipe"));
		 
		
		verify(recipeService, times(1)).findCommandById(anyLong());
		
		
	}
	
	@Test
	public void testShowIngredient() throws Exception{
		
		IngrediantsCommand command = new IngrediantsCommand();
		command.setId(1l);
		
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
		
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(get("/recipe/1/ingredient/2/show")).andExpect(view().name("showIngredient"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("ingredient"));	
		
		verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
	
	}
	
	@Test
	public  void testUpdateRecipeIngredieant() throws Exception{
		
//given
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		
		
		IngrediantsCommand command = new IngrediantsCommand();
		command.setId(1l);
		
		List<UnitOfMeasureCommand> list = Arrays.asList( new UnitOfMeasureCommand());
		
		when(ingredientService.listAllUoms()).thenReturn(list);
		
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);

//		when
		mockMvc.perform(get("/recipe/1/ingredient/1/update")).andExpect(view().name("ingredientForm"))
		.andExpect(status().isOk()).andExpect(model().attributeExists("ingredient")).andExpect(model().attributeExists("uoms"));
		
	
//then
	verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
	verify(ingredientService, times(1)).listAllUoms();
	
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception{
		
		IngrediantsCommand command = new IngrediantsCommand();
		command.setRecipeId(1l);
		command.setId(1l);
		
		when(ingredientService.saveIngredientCommand(anyObject())).thenReturn( command);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform( post("/recipe/1/ingredient"))
				 .andExpect( status().is3xxRedirection() )
				  .andExpect( view().name("redirect:/recipe/1/ingredient/1/show"));
	
		
//		then
		verify(ingredientService, times(1)).saveIngredientCommand(anyObject());
		
	}
	
	@Test
	public void testAddIngredient() throws Exception{
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		when(recipeService.findCommandById(anyLong())).thenReturn(new RecipeCommand());
		
		mockMvc.perform( get("/recipe/1/ingredient/new") )
			    .andExpect( status().isOk() ).andExpect(view().name("ingredientForm"))
			     .andExpect(model().attributeExists("ingredient"));
		
		verify(recipeService, times(1)).findCommandById(anyLong());
		
	}
	
	@Test
	public void testDeletIngredient() throws Exception{
		
		MockMvc build = MockMvcBuilders.standaloneSetup(controller).build();
		
		
//		when(ingredientService.deleteById(anyLong()));
		
		build.perform( get("/recipe/1/ingredient/1/delete"))
	      .andExpect( status().is3xxRedirection())
		.andExpect( view().name("redirect:/recipe/1/ingredients"));
		
 
		
		
	}
	
}
