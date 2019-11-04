package net.casesr.recipeapp.services;

import java.util.Set;

import net.casesr.recipeapp.commands.RecipeCommand;
import net.casesr.recipeapp.domain.Recipe;

public interface RecipeService {
	
	Set<Recipe> getRecipes();

	Recipe findById(Long id);

	void deleteById(Long id);

	RecipeCommand findCommandById(Long id);

	RecipeCommand saveRecipeCommand(RecipeCommand command);

}
