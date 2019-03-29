package com.recipe.service;

import java.util.Set;

import com.recipe.command.RecipeCommand;
import com.recipe.domain.Recipe;

public interface RecipeService {

    Set<Recipe> getRecipes();

	Recipe findById(Long l);

	RecipeCommand findCommandById(Long l);

	RecipeCommand saveRecipeCommand(RecipeCommand command);

	void deleteById(Long idToDelete);
}