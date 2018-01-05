package com.me.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.command.NotesCommand;
import com.me.command.RecipeCommand;
import com.me.model.Recipe;


@RunWith(PowerMockRunner.class)
public class RecipeCommandToRecipeTest {
	

	
	@Mock
	private NotesCommandToNotes notesConverter;

	@Mock
	private CategoryCommandToCategory categoryConverter;
	
	@InjectMocks
	private RecipeCommandToRecipe converter;
	
	@Before
	
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testConvert() {
		
		RecipeCommand command = new RecipeCommand();
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setNotes("notes");
		
		command.setCategories(new ArrayList<>());
		command.setCookTime(1);
		command.setDescription("description");
		command.setDirection("direction");
		
		
		command.setNotes(notesCommand);
		
		command.setIngrediants(new ArrayList<>());
		
		Recipe recipe = converter.convert(command);
		
		
		assertEquals("description", recipe.getDescription());
		assertEquals("direction", recipe.getDirection());
		
		assertNotNull(recipe.getCategories());
		assertNotNull(recipe.getIngrediants());
	
	}

	
}
