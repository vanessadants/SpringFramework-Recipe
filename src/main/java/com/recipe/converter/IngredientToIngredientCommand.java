package com.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.command.IngredientCommand;
import com.recipe.domain.Ingredient;

import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
	
	private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

	public IngredientToIngredientCommand(
			UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
		this.uomConverter = uomConverter;
	}
	@Synchronized
    @Nullable
    @Override
	public IngredientCommand convert(Ingredient ingredient) {
		 if (ingredient == null) {
	            return null;
	        }

	        IngredientCommand ingredientCommand = new IngredientCommand();
	        ingredientCommand.setId(ingredient.getId());
	        if (ingredient.getRecipe() != null) {
	            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
	        }
	        ingredientCommand.setAmount(ingredient.getAmount());
	        ingredientCommand.setDescription(ingredient.getDescription());
	        ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));
	        return ingredientCommand;
	}

}
