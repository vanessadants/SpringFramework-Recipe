package com.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
    
}
