package net.casesr.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import net.casesr.recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
