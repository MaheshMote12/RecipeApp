package com.me.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.me.command.RecipeCommand;
import com.me.model.Recipe;
import com.me.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImageController {

	private RecipeService recipeService;

	@Autowired
	public ImageController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	@RequestMapping(value="/recipe/{recipeId}/image", method=RequestMethod.GET)
	public String getImageForm(@PathVariable("recipeId") Long recipeId, Model model){
		
		Recipe recipe = recipeService.findRecipeById( recipeId );
		
		model.addAttribute("recipe", recipe);
		
		model.addAttribute("userclickUploadImage", true);
		
		return "index";
		
	}
	
	@RequestMapping(value="/recipe/{recipeId}/image", method=RequestMethod.POST)
	public String saveImage(@PathVariable("recipeId") Long recipeId, @RequestParam("imagefile") MultipartFile file, Model model){
		
		
		recipeService.saveImageFile(recipeId, file);
		
		return "redirect:/recipe/show/"+recipeId;
		
	}
	
	@GetMapping("/recipe/{recipeId}/recipeImage")
	public @ResponseBody void renderImageFromDB(@PathVariable("recipeId") Long recipeId, HttpServletResponse response){
		
		RecipeCommand command = recipeService.findCommandById(recipeId);
		
		Byte[] image = command.getImage();
		
		response.setHeader("Content-Disposition", "inline;filename=file");
		response.setHeader("Content-Length", String.valueOf( image.length ));
		
		byte[] byteArr =  new byte[image.length];
		
		int i = 0;
		
		for(byte b : image){
			byteArr[i++] = b;
		}
		
		
		try {
			
			FileCopyUtils.copy(byteArr, response.getOutputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Image Not Found for goven recipe");
			e.printStackTrace();
		}
		
	}
	
	
	
}
