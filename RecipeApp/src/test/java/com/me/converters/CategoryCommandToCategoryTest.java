package com.me.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.command.CategoryCommand;
import com.me.converters.CategoryCommandToCategory;
import com.me.model.Category;

@RunWith(PowerMockRunner.class)
public class CategoryCommandToCategoryTest {

	
	private static final Long Long_Value = 1l;
	private static final String categoryName = "Spicy";
	
//	@Mock
	Category category;
	
//	@Mock
	CategoryCommand categoryCommand;
	
//	@InjectMocks
	CategoryCommandToCategory converter;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		converter = new CategoryCommandToCategory();
	}

	@Test
	public void testNullParameter() {
		
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	
	@Test
	public void testConvert() {
		
//		setup
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setCategoryId(Long_Value);
		categoryCommand.setCategoryName(categoryName);
	
//		execution
		Category category = converter.convert(categoryCommand);
		
		assertNotNull(category);
		assertEquals(Long_Value, category.getCategoryId());
		assertEquals(categoryName, category.getCategoryName());
		
	}
	
}
