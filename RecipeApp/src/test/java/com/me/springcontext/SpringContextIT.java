package com.me.springcontext;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.me.model.UnitOfMeasure;
import com.me.repository.RecipeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:root-context.xml", /*"classpath:servlet-context.xml"*/})
public class SpringContextIT {

	@Autowired
	private RecipeRepository recipeRepository;
	
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
		
		UnitOfMeasure uom = recipeRepository.getUom("scoop");
		
		assertEquals("scoop", uom.getUnit());
		
	}
	

	@Test
	@DirtiesContext
	public void getUOMtable_spoonIT(){
		
		UnitOfMeasure uom = recipeRepository.getUom("table spoon");
		
		assertEquals("table spoon", uom.getUnit());
		
	}

	

}
