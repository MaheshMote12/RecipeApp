package com.me.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.me.command.RecipeCommand;
import com.me.converters.RecipeCommandToRecipe;
import com.me.converters.RecipeToRecipeCommand;
import com.me.model.Recipe;
import com.me.repository.RecipeRepository;
import com.me.serviceImpl.RecipeServiceImpl;

@RunWith(PowerMockRunner.class)
public class RecipeServiceImplTest {

	@Mock
	private RecipeCommandToRecipe toRecipe;
	@Mock
	private RecipeToRecipeCommand toRecipeCommand;
	@Mock
	private RecipeRepository recipeRepo;
	@InjectMocks
	private RecipeServiceImpl recipeService; 
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
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
	
	@Test
	public void findCommandByIdTest(){
		
		
		when(recipeRepo.findById(anyLong())).thenReturn(anyObject());
		when(toRecipeCommand.convert(new Recipe())).thenReturn(new RecipeCommand());
		
		
		RecipeCommand recipeCommand = recipeService.findCommandById(anyLong());
		
		verify(recipeRepo, times(1)).findById(anyLong());
		verify(toRecipeCommand, times(1)).convert(anyObject());
	}

	
	@Test
	public void deleteByIdTest(){
		
//		given
		
//		when
		recipeService.deleteById(Long.valueOf(2));
		
//		then
		verify(recipeRepo, times(1)).deleteById(anyLong());
	}
	
	@Test
	public void testSaveImageFile() throws IOException{
		
//		given
		long id =1l;
		MultipartFile multipartFile = new MockMultipartFile("imagFile", "file", MediaType.IMAGE_JPEG_VALUE, "this is file".getBytes() );
		
		
		
		Recipe recipe = new Recipe();
		recipe.setRecipeId(1l);
		
		when(recipeRepo.findById(anyLong())).thenReturn(recipe);
		
		ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
		
//		when
		
		recipeService.saveImageFile(id, multipartFile);

//		then
		
		verify(recipeRepo, times(1)).mergeRecipe(captor.capture());
		Recipe recipe2 = captor.getValue();
		assertEquals(multipartFile.getBytes().length, recipe2.getImage().length);
	}
	
	
}
