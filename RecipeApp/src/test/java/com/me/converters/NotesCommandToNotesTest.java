package com.me.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.command.NotesCommand;
import com.me.model.Notes;

@RunWith(PowerMockRunner.class)
public class NotesCommandToNotesTest {

	@Mock
	private RecipeCommandToRecipe recipeConverter;
	
	@InjectMocks
	NotesCommandToNotes converter;
	
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
		assertNotNull(converter.convert(new NotesCommand()));
	}

	@Test
	public void testConvert() {
		
		NotesCommand command = new NotesCommand();
		command.setId(12l);
		command.setNotes("Delicious");
		
		Notes notes = converter.convert(command);
		
		assertEquals("Delicious", notes.getNotes());
		assertThat(12l, is(notes.getId()));
	}

}
