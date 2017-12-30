package com.me.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.me.command.IngrediantsCommand;
import com.me.model.Ingrediants;

public class IngrediantsCommandToIngrediantsTest {

	IngrediantsCommandToIngrediants converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new IngrediantsCommandToIngrediants();
	}

	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new IngrediantsCommand()));
	}

	
	@Test
	public void testConvert() {

		IngrediantsCommand command = new IngrediantsCommand();  
		command.setAmount(new BigDecimal(12));
		command.setDescription("spicy");
	
		
		Ingrediants ingrediants  = converter.convert(command);
		
		assertThat(new BigDecimal(12), is(ingrediants .getAmount()));
		assertThat("spicy", is(ingrediants.getDescription()));
	}

}
