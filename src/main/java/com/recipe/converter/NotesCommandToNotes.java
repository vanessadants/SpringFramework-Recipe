package com.recipe.converter;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.recipe.command.NotesCommand;
import com.recipe.domain.Notes;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
	
	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand source) {
		if (source == null) {
			return null;
		}

		final Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setRecipeNotes(source.getRecipeNotes());
		return notes;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		System.out.println("unimplemented " + Object.class.getName() + "  " + Object.class.getClass().getMethods());
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		System.out.println("unimplemented " + Object.class.getName() + "  " + Object.class.getClass().getMethods());
		return null;
	}

}
