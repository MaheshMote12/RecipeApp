package com.me.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.me.command.IngrediantsCommand;
import com.me.command.UnitOfMeasureCommand;
import com.me.converters.IngrediantsToIngrediantsCommand;
import com.me.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.me.model.Ingrediants;
import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;
import com.me.repository.IngredientRepository;
import com.me.repository.RecipeRepository;
import com.me.repository.UnitOfMeasureRepo;
import com.me.serviceImpl.IngredientServiceImpl;

import jdk.nashorn.internal.ir.annotations.Ignore;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value=Optional.class)
public class IngredientServiceImplTest {

	@Mock
	RecipeRepository recipeRepo;
	@Mock
	IngredientRepository ingrediantsRepo;
	@Mock
	UnitOfMeasureCommand uomC ;
	@Mock
	private UnitOfMeasureRepo uomRepo;
	@Mock
	IngrediantsToIngrediantsCommand ingrToCommand;
	@Mock
	IngrediantsCommand ingrCommand;
	@Mock
	UnitOfMeasureToUnitOfMeasureCommand uomToCommand;
	@Captor
	ArgumentCaptor<Optional<Recipe>> captor;
	
	@InjectMocks
	IngredientServiceImpl ingredientService ;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); 

//		this.ingredientService = new IngredientServiceImpl(recipeRepo, ingrediantsRepo, ingrToCommand, uomToCommand);
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
		when(uomToCommand.convert(anyObject())).thenReturn( new UnitOfMeasureCommand() );
//		when
		
		List<UnitOfMeasureCommand> uoms = ingredientService.listAllUoms();
//		then
		
		
		assertNotNull(uoms);
		verify(ingrediantsRepo, times(1)).finadAllUoms();		
	}
	
	@Ignore
	public void testSaveIngredientCommand(){
		
		IngrediantsCommand command = new IngrediantsCommand();
		command.setId(1l);
		command.setRecipeId(2l);
		command.setDescription("something");
		UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
		uomc.setId(1l);
		command.setUomC(uomc);
		
		
		Ingrediants ingrediants = new Ingrediants();
		ingrediants.setId(1l);
		
		Recipe recipe = new Recipe();
		recipe.setRecipeId(2l);
		recipe.addIngredients(ingrediants);
		
		Recipe savedRecipe = new Recipe();
		savedRecipe.setRecipeId(2l);
		savedRecipe.addIngredients(new Ingrediants());
		savedRecipe.getIngrediants().iterator().next().setId(1l);
		
		Optional<Recipe> optional = Optional.ofNullable(recipe);
		
		PowerMockito.mockStatic(Optional.class);
		
		PowerMockito.when( Optional.ofNullable(any(Recipe.class))).thenReturn( optional );
		when(ingrCommand.getRecipeId()).thenReturn(Long.valueOf(1));
		when(recipeRepo.findById(anyObject())).thenReturn( recipe);
		when(recipeRepo.save(anyObject())).thenReturn( savedRecipe );
		when(uomRepo.findById(anyLong())).thenReturn(new UnitOfMeasure());
		when(ingrCommand.getUomC()).thenReturn(new UnitOfMeasureCommand());
		when(uomC.getId()).thenReturn(1l);
		when(ingrToCommand.convert(anyObject())).thenReturn(  command);
//		when(ingrediantsRepo.findByRecipeAndIngrediantsId(anyLong(), anyLong())).thenReturn();
		
		IngrediantsCommand ingrediantsCommand = ingredientService.saveIngredientCommand(command);
		
		
		
//		PowerMockito.verifyStatic(Optional.class);
//		ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
		
//		Optional.ofNullable(captor.capture() );
		
//		assertNotNull(captor.getValue());
		assertThat(1l, is(ingrediantsCommand.getId()));
		verify(recipeRepo, times(1)).findById(anyLong());
		verify(recipeRepo, times(1)).save(any(Recipe.class));
	}

	@Test
	public void testDeleteIngredientByRecipeIdIngrId(){
		
	
		ingredientService.deleteByRecipeIdAndIngredientId(anyLong(), anyLong());

		
		
		
	}
	
	
}
