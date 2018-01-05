package com.me.controllers;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.me.command.RecipeCommand;
import com.me.service.RecipeService;

@RunWith(PowerMockRunner.class)
public class IngredientsControllerTest {

	
	@Mock
	private RecipeService recipeService;
	
	private IngredientsController controller;
	
	@Before
	public void before(){
		controller = new IngredientsController(recipeService);
	}
	
	
	@Test
	public void viewIngredientsTest() throws Exception{
		
		RecipeCommand command = new RecipeCommand();
		
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(get("/recipe/1/ingredients")).andExpect(view().name("index"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("recipe"));
		 
		
		verify(recipeService, times(1)).findCommandById(anyLong());
		
		
	}
	
	
}
