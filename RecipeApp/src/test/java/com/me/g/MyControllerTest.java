package com.me.g;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.Model;

import com.me.controllers.MyController;
import com.me.model.Recipe;
import com.me.repository.RecipeRepository;


@RunWith(PowerMockRunner.class)
public class MyControllerTest {

	@Mock
	private RecipeRepository recipeRepo;
	@Mock
	private Model model;
	
	
	@InjectMocks
	private MyController myController;
	
	@Captor
	ArgumentCaptor<List<Recipe>> captor;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHomeMethod() {
		
		
		// given
		List<Recipe> list = Arrays.asList(new Recipe(), new Recipe() );
		
		when(recipeRepo.getAllRecipies()).thenReturn(list);
		

		
		//when
		String home = myController.home( model);
		
		
		//then
		verify(recipeRepo, times(1)).getAllRecipies();
		
		verify(model, times(1)).addAttribute(eq("recipies"), anyListOf(Recipe.class));
		
//		capturing arguments
		verify(model, times(1)).addAttribute(eq("recipies"), captor.capture());
		
		List<Recipe> value = captor.getValue();
		
		
		assertEquals(2, value.size());
		
		assertThat("home", is(home));

	}
	
	

}
