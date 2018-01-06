package com.me.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.command.IngrediantsCommand;
import com.me.command.UnitOfMeasureCommand;
import com.me.converters.IngrediantsToIngrediantsCommand;
import com.me.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.me.model.Ingrediants;
import com.me.model.UnitOfMeasure;
import com.me.repository.IngredientRepository;
import com.me.repository.RecipeRepository;
import com.me.serviceImpl.IngredientServiceImpl;


@RunWith(PowerMockRunner.class)
public class IngredientServiceImplTest {

	@Mock
	RecipeRepository recipeRepo;
	@Mock
	IngredientRepository ingrediantsRepo;
	@Mock
	IngrediantsToIngrediantsCommand toCommand;
	@Mock
	UnitOfMeasureToUnitOfMeasureCommand toUom;
	
	IngredientServiceImpl ingredientService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); 

		this.ingredientService = new IngredientServiceImpl(recipeRepo, ingrediantsRepo, toCommand, toUom);
	}

	@Test
	public void testFindByRecipeIdAndIngredientId() {

		Ingrediants ingr = new Ingrediants();
		
		when(ingrediantsRepo.findByRecipeAndIngrediantsId(anyLong(), anyLong())).thenReturn(ingr);
	
		IngrediantsCommand command = ingredientService.findByRecipeIdAndIngredientId(1l, 2l);
		
		verify(ingrediantsRepo, times(1)).findByRecipeAndIngrediantsId(anyLong(), anyLong());
		
	}
	
	@Test
	public void testListAllUoms(){
		
//		given

		List<UnitOfMeasure> list = Arrays.asList(new UnitOfMeasure(), new UnitOfMeasure());
		 
		when(ingrediantsRepo.finadAllUoms()).thenReturn(list);
		when(toUom.convert(anyObject())).thenReturn( new UnitOfMeasureCommand() );
//		when
		
		List<UnitOfMeasureCommand> uoms = ingredientService.listAllUoms();
//		then
		
		
		assertNotNull(uoms);
		verify(ingrediantsRepo, times(1)).finadAllUoms();		
	}
	

}
