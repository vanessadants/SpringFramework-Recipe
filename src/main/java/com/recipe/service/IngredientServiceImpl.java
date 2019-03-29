package com.recipe.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.recipe.command.IngredientCommand;
import com.recipe.converter.IngredientCommandToIngredient;
import com.recipe.converter.IngredientToIngredientCommand;
import com.recipe.domain.Ingredient;
import com.recipe.domain.Recipe;
import com.recipe.exception.NotFoundException;
import com.recipe.repository.RecipeRepository;
import com.recipe.repository.UnitOfMeasureRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) throws NotFoundException {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			// todo impl error handling
			throw new NotFoundException("recipe id not found. Id: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			// todo impl error handling
			throw new NotFoundException("Ingredient id not found: " + ingredientId);
		}

		return ingredientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		if (!recipeOptional.isPresent()) {

			// todo toss error if not found!
			System.out.println("Recipe not found for ID: " + command.getRecipeId());
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingridient -> ingridient.getId().equals(command.getId())).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
						.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); // todo
																					// address
																					// this
			} else {
				// add new Ingredient
				Ingredient ingredient = ingredientCommandToIngredient.convert(command);
				ingredient.setRecipe(recipe);
				recipe.addIngredient(ingredient);
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId())).findFirst();

			// check by description
			if (!savedIngredientOptional.isPresent()) {
				// not totally safe... But best guess
				savedIngredientOptional = savedRecipe.getIngredients().stream()
						.filter(recipeIngredients -> recipeIngredients.getDescription()
								.equals(command.getDescription()))
						.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
						.filter(recipeIngredients -> recipeIngredients.getUom().getId()
								.equals(command.getUom().getId()))
						.findFirst();
			}

			// to do check for fail
			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
		}
	}

	@Override
	public void deleteById(Long recipeId, Long idToDelete) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			System.out.println("found recipe");

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(idToDelete)).findFirst();

			if (ingredientOptional.isPresent()) {
				System.out.println("found Ingredient");
				Ingredient ingredientToDelete = ingredientOptional.get();
				ingredientToDelete.setRecipe(null);
				recipe.getIngredients().remove(ingredientOptional.get());
				recipeRepository.save(recipe);
			}
		} else {
			System.out.println("Recipe Id Not found. Id:" + recipeId);
		}
	}

}