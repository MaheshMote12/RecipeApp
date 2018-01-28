package com.me.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.me.command.RecipeCommand;
import com.me.converters.CategoryCommandToCategory;
import com.me.converters.RecipeCommandToRecipe;
import com.me.converters.RecipeToRecipeCommand;
import com.me.exceptions.CategoryNotFountException;
import com.me.exceptions.RecipeNotFoundException;
import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;
import com.me.repository.RecipeRepository;
import com.me.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{
	
	private RecipeCommandToRecipe toRecipe;
	
	private RecipeToRecipeCommand toRecipeCommand;
	
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private CategoryCommandToCategory categoryToCommand;

	
	
	public RecipeServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public RecipeServiceImpl( RecipeRepository recipeRepository, RecipeToRecipeCommand toRecipeCommand, RecipeCommandToRecipe toRecipe) {
		super();
		this.recipeRepository = recipeRepository;
		this.toRecipe = toRecipe;
		this.toRecipeCommand = toRecipeCommand;
	}
	
	@Override
	public RecipeCommand saveRecipe(RecipeCommand recipeCommand)
	{
		Recipe detachedRecipe = toRecipe.convert(recipeCommand);
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		
		return toRecipeCommand.convert(savedRecipe);
		
	}
	
	@Override
	public UnitOfMeasure getUOM(String unit)
	{
		return recipeRepository.getUom(unit);
	}

	@Override
	public Recipe findRecipeById(Long id) {

		Recipe recipe = recipeRepository.findById(id);
		
		if(recipe == null)
		{
			throw new RecipeNotFoundException("User Is Not Present For ID Value: "+ id.toString());
		}
		return recipe;
		 
	}

	@Override
	public List<Recipe> getRecipies() {
		
		return recipeRepository.getRecipies();
	}

	@Override
	public List<String> getCategories() {

		  List<String> categories = recipeRepository.findCategories();
		  
		 
		
		if(categories == null || categories.size() < 0 ){
			throw new CategoryNotFountException("Category Not Found ");
		}
		
		return categories;
	}

	@Override
	public RecipeCommand updateRecipe(RecipeCommand recipeCommand) {

		Recipe recipe = recipeRepository.save(toRecipe.convert(recipeCommand));
		
		
		
		return null;
	}

	@Override
	public RecipeCommand findCommandById(Long id) {

		Recipe findById = recipeRepository.findById(id);
		RecipeCommand command = toRecipeCommand.convert(findById);
		
		return command;
	}

	@Override
	public void deleteById(Long id) {
		if(id == null){
			throw new RecipeNotFoundException("No Recipe To Delete");
		}
		recipeRepository.deleteById(id);
	}

	@Override
	public void saveImageFile(long recipeId, MultipartFile file) {

		try {
		
			Recipe recipe = recipeRepository.findById(recipeId);

			int i = 0;
			Byte[] byteObjects = new Byte[file.getBytes().length];
			
			
			for(byte b : file.getBytes()){
				byteObjects[i++] = b;
				
			}
			
			recipe.setImage(byteObjects);
			
			recipeRepository.mergeRecipe(recipe);
		
		
		} catch (IOException e) {

//			handle better
			log.error("error occured");
			
			e.printStackTrace();
		}
		
		
		
	}
	
}
