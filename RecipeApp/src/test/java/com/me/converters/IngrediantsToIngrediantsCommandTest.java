package com.me.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.command.IngrediantsCommand;
import com.me.model.Ingrediants;
import com.me.model.Recipe;

@RunWith(PowerMockRunner.class)
public class IngrediantsToIngrediantsCommandTest {

	@Mock
	private RecipeToRecipeCommand recipeConverter;
	
	@Mock
	private UnitOfMeasureToUnitOfMeasureCommand uomConverter;
	@InjectMocks
	IngrediantsToIngrediantsCommand converter;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		converter.convert(null);
	}

	@Test
	public void testConvert() {

		Ingrediants ingrediants = new Ingrediants();
		ingrediants.setDescription("spicy");
		ingrediants.setId(1l);
		
		Recipe recipe = new Recipe();
		recipe.setRecipeId(1l);
		ingrediants.setRecipe(recipe );
		
		
		
		IngrediantsCommand command = converter.convert(ingrediants);
		
		assertEquals("spicy", command.getDescription());
		assertThat(1l, is(command.getId()));
	}

}
