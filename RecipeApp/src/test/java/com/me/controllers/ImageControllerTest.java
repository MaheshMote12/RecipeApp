package com.me.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.me.command.RecipeCommand;
import com.me.model.Recipe;
import com.me.service.RecipeService;

@RunWith(PowerMockRunner.class)
public class ImageControllerTest {

	@Mock
	RecipeService recipeService;
	
	@InjectMocks
	ImageController controller;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		
		mockMvc = MockMvcBuilders.standaloneSetup( controller).build();
		
	}

	@Test
	public void testGetImpageForm() throws Exception{
		
		Recipe recipe = new Recipe();
		recipe.setRecipeId(1l);
		
		when(recipeService.findRecipeById(anyLong() )).thenReturn(recipe);
		
		mockMvc.perform( get("/recipe/1/image")).andExpect( model().attributeExists("recipe"))
			    .andExpect( view().name("index") );

		
		assertThat(1l, is(recipe.getRecipeId()));
		verify(recipeService, times(1)).findRecipeById(anyLong());
		
		
		
	}

	@Ignore
	@Test
	public void testImageForm() throws Exception {
		
		MockMultipartFile multipartFile = new MockMultipartFile("file", "multipartFile", MediaType.IMAGE_JPEG_VALUE, "this is sample".getBytes());
		
		mockMvc.perform( post("/recipe/1/image") )
				.andExpect(status().is3xxRedirection());	
		
		
//		mockMvc.perform(  )
		
		
		verify(recipeService, times(1)).saveImageFile(anyLong(), any());
	}

	@Test
	public void testRenderImageFromDB() throws Exception{
		
		RecipeCommand recipe = new RecipeCommand();
		recipe.setRecipeId(1l);
		
		String s = "something";
		
		Byte[] b = new Byte[s.getBytes().length];
		
		int i = 0;
		for(byte by : s.getBytes()){
			b[i++] = by;
		}
		
		recipe.setImage(b);
		
		when(recipeService.findCommandById(anyLong())).thenReturn(recipe);
		
		MockHttpServletResponse response = mockMvc.perform( get("/recipe/1/recipeImage"))
				.andExpect( status().isOk())
				 .andReturn().getResponse();
		
		
		assertEquals(s.getBytes().length, response.getContentAsByteArray().length);
		
		
		
	}
	
	
}
