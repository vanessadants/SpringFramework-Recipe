package com.recipe.service;

import com.recipe.command.IngredientCommand;
import com.recipe.exception.NotFoundException;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) throws NotFoundException;

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	void deleteById(Long valueOf, Long valueOf2);
	
}
