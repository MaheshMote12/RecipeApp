package com.me.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.command.NotesCommand;
import com.me.command.RecipeCommand;
import com.me.model.Notes;
import com.me.model.Recipe;

@RunWith(PowerMockRunner.class)
public class RecipeToRecipeCommandTest {
	
	@Mock
	CategoryCommandToCategory categoryCommand;
	@Mock
	private RecipeCommandToRecipe recipeConverter;
	@Mock
	private NotesToNotesCommand notesConverter;
	@Mock
	private CategoryToCategoryCommand categoryConverter;
	@Mock
	private IngrediantsToIngrediantsCommand ingrediantsCommand;

	
	
	@InjectMocks
	RecipeToRecipeCommand converter;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		converter.convert(new Recipe());
	}

	@Test
	public void testConvert() {

		Recipe recipe = new Recipe();
		recipe.setRecipeId(12l);
		recipe.setCookTime(1);
		recipe.setCategories(new HashSet<>());
		recipe.setDescription("delicious");
		recipe.setDirection("direcition");
		recipe.setImage(new Byte[12]);
		recipe.setIngrediants(new ArrayList<>());
		recipe.setNotes(new Notes());
		recipe.setPrepTime(2);
		recipe.setServings(5);
		recipe.setSource("google");
		recipe.setUrl("url");
		
		when(notesConverter.convert(anyObject())).thenReturn(new NotesCommand());
		
		RecipeCommand command = converter.convert(recipe);
		
		assertThat(12l, is(command.getRecipeId()));
		assertThat(1, is(command.getCookTime()));
		assertEquals("delicious", command.getDescription());
		
		assertNotNull(command.getCategories());
		assertNotNull(command.getIngrediants());
		assertNotNull(command.getNotes());
		assertNotNull(command.getImage());
		
		assertEquals("google", command.getSource());
		assertEquals("url", command.getUrl());
		assertThat(2, is(command.getPrepTime()));
		assertThat(5, is(command.getServings()));

	}

}
