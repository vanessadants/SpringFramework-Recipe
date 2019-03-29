package com.recipe.converter;

import lombok.Synchronized;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.recipe.command.CategoryCommand;
import com.recipe.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
	
	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if (source == null)
			return null;

		final Category category = new Category();
		category.setId(source.getId());
		category.setDescription(source.getDescription());
		return category;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		System.out.println("unimplemented CategoryCommandToCategory getInputType()");
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		System.out.println("unimplemented CategoryCommandToCategory getOutputType");
		return null;
	}

}
