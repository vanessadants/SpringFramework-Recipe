package com.recipe.converter;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.recipe.command.IngredientCommand;
import com.recipe.domain.Ingredient;
import com.recipe.domain.Recipe;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {

		this.uomConverter = uomConverter;
	}

	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null) {
			return null;
		}
		final Ingredient ingredient = new Ingredient();

		if (source.getRecipeId() != null) {
			Recipe recipe = new Recipe();
			recipe.setId(source.getRecipeId());
			ingredient.setRecipe(recipe);
			recipe.addIngredient(ingredient);
		}

		ingredient.setAmount(source.getAmount());
		ingredient.setDescription(source.getDescription());
		ingredient.setUom(uomConverter.convert(source.getUom()));
		return ingredient;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		System.out.println("unimplementeg IngredientCommandToIngredient getInputType");
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		System.out.println("unimplementeg IngredientCommandToIngredient getOutputType");
		return null;
	}

}
