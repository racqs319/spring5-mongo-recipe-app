package net.casesr.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.casesr.recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
	
	Optional<UnitOfMeasure> findByDescription(String description);

}
