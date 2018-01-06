package com.me.springcontext;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.me.controllers.IngredientsController;
import com.me.model.UnitOfMeasure;
import com.me.repository.RecipeRepository;
import com.me.service.IngredientService;
import com.me.service.RecipeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:root-context.xml", /*"classpath:servlet-context.xml"*/})
public class SpringContextIT {

	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	RecipeService recip;
	@Autowired
	IngredientService ing;
	
//	private IngredientsController controller = new IngredientsController(recip, ing);
	
	@Autowired
	IngredientsController controller;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=RuntimeException.class)
	public void test() {
		throw new RuntimeException();
	}
	
	@Test
	public void getUOMIT(){
		
		UnitOfMeasure uom = recipeRepository.getUom("Tab");
		
		assertEquals("Tab", uom.getUnit());
		
	}
	

	@Test
	public void getUOMtable_spoonIT(){
		
		UnitOfMeasure uom = recipeRepository.getUom("Tab");
		
		assertEquals("Tab", uom.getUnit());
		
	}

/*	@Test
	public void ShowIngredientIT() throws Exception{
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(get("/recipe/1/ingredient/1/show")).andExpect(view().name("showIngredient"))
				.andExpect(status().isOk()).andExpect(model().attributeExists("ingredient"));	
		
		
		
	}
*/
}
