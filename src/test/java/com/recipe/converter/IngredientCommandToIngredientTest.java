package com.recipe.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.recipe.command.IngredientCommand;
import com.recipe.command.UnitOfMeasureCommand;
import com.recipe.domain.Ingredient;
import com.recipe.domain.Recipe;

public class IngredientCommandToIngredientTest {

	public static final Recipe RECIPE = new Recipe();
	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final String DESCRIPTION = "Cheeseburger";
	public static final Long ID_VALUE = new Long(1L);
	public static final Long UnitOfMeasureCommand_ID = new Long(2L);

	IngredientCommandToIngredient converter;

	@Before
	public void setUp() throws Exception {
		converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	public void testNullObject() throws Exception {
		assertNull(converter.convert(null));
	}

	@Ignore
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new IngredientCommand()));
	}

	@Test
	public void convert() throws Exception {
		// given
		IngredientCommand command = new IngredientCommand();
		command.setId(ID_VALUE);
		command.setAmount(AMOUNT);
		command.setDescription(DESCRIPTION);
		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setId(UnitOfMeasureCommand_ID);
		command.setUom(unitOfMeasureCommand);

		// when
		Ingredient ingredient = converter.convert(command);

		// then
		assertNotNull(ingredient);
		assertNotNull(ingredient.getUom());
		// assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(UnitOfMeasureCommand_ID, ingredient.getUom().getId());
	}

	@Test
	public void convertWithNullUnitOfMeasureCommand() throws Exception {
		//given
		IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
       // assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
	}

}