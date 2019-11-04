package net.casesr.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.casesr.recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
	
	Optional<Category> findByDescription(String description);

}
