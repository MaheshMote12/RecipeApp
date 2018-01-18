package com.me.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.me.command.IngrediantsCommand;
import com.me.model.Ingrediants;

@RunWith(MockitoJUnitRunner.class)
public class IngrediantsCommandToIngrediantsTest {

	@Mock
	private UnitOfMeasureCommandToUnitOfMeasure uom;
	
	@InjectMocks
	IngrediantsCommandToIngrediants converter;
	
	@Before
	public void setUp() throws Exception {

	}

	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		UnitOfMeasureCommandToUnitOfMeasure mock2 = mock(UnitOfMeasureCommandToUnitOfMeasure.class);
		when(mock2.convert(anyObject())).thenReturn(anyObject());
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
