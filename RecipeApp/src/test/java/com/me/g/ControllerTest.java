/*package com.me.g;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.me.model.Recipe;
import com.me.repository.RecipeRepository;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

	
	@Mock
	private RecipeRepository recipeRepo;
	
	@Mock
	private PersonModel pm ;

	@InjectMocks
	private MyController  myController;
	
	@Test
	public void mockMvcTest() throws Exception  {
		
//		given
		List<Recipe> list = Arrays.asList(new Recipe(), new Recipe() );
		
		when(recipeRepo.getAllRecipies()).thenReturn(list);
		
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"));

		
	}
}
*/