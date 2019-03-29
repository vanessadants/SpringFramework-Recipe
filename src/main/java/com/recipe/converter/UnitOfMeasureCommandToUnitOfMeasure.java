package com.recipe.converter;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.recipe.command.UnitOfMeasureCommand;
import com.recipe.domain.UnitOfMeasure;

import lombok.Synchronized;


@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
	
	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if (source == null) {
			return null;
		}
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());

		return uom;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		System.out.println("unimplementeg UnitOfMeasureCommandToUnitOfMeasure getInputType");
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		System.out.println("unimplementeg UnitOfMeasureCommandToUnitOfMeasure getOutputType");
		return null;
	}

}
