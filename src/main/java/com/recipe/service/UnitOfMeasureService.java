package com.recipe.service;

import java.util.Set;

import com.recipe.command.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
	
	 Set<UnitOfMeasureCommand> listAllUoms();
	 
}
