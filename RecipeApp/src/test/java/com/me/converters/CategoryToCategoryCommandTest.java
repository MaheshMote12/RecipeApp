package com.me.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.command.CategoryCommand;
import com.me.model.Category;
import com.me.model.Recipe;

@RunWith(PowerMockRunner.class)
public class CategoryToCategoryCommandTest {

	@Mock
	private RecipeToRecipeCommand recipeConverter;

	@InjectMocks
	private CategoryToCategoryCommand converter;
	
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
		assertNotNull(converter.convert(new Category()	));
	}
	
	@Test
	public void testConvert() {
		
		HashSet<Recipe> set = new HashSet<>();
		set.add(new Recipe());
		
		Category category = new Category();
		category.setCategoryId(1l);
		category.setCategoryName("spicy");
		category.getRecipies().addAll(set);
		
		CategoryCommand command = converter.convert(category);

		assertThat("spicy", is(command.getCategoryName()));
		assertThat(1l, is(command.getCategoryId()));
		assertThat(set.size(), is(command.getRecipies().size()));
		
		
	}
	
	
	
	
}
