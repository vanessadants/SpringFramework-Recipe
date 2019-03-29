package com.recipe.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.recipe.command.RecipeCommand;
import com.recipe.converter.RecipeCommandToRecipe;
import com.recipe.converter.RecipeToRecipeCommand;
import com.recipe.domain.Recipe;
import com.recipe.exception.NotFoundException;
import com.recipe.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {

		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long l) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		if (!recipeOptional.isPresent()) {
			throw new NotFoundException("Recipe not found for ID value " + l);
		}
		return recipeOptional.get();
	}

	@Override
	@Transactional
	public RecipeCommand findCommandById(Long l) {
		return recipeToRecipeCommand.convert(findById(l));
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
		Recipe saveRecipe = recipeRepository.save(detachedRecipe);
		return recipeToRecipeCommand.convert(saveRecipe);
	}

	@Override
	public void deleteById(Long idToDelete) {
		recipeRepository.deleteById(idToDelete);

	}
}