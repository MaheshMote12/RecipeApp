package com.me.repositoryImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.model.Ingrediants;
import com.me.model.UnitOfMeasure;
import com.me.repository.IngredientRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class IngredientRepositoryImplIT {

	@Autowired
	public IngredientRepository ingrRepo;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		
	}

	@Test
	public void testFindByRecipeAndIngrediantsId() {

		Ingrediants ingrediants = ingrRepo.findByRecipeAndIngrediantsId(1l, 1l);
		
		assertThat(1l, is(ingrediants.getId()));
	
	}
	
	@Test
	public void testListAllUoms(){
		
		List<UnitOfMeasure> uoms = ingrRepo.finadAllUoms();
		
		assertNotNull(uoms);
		
	}

}
