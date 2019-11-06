package net.casesr.recipeapp.services;

import java.util.Set;

import net.casesr.recipeapp.commands.RecipeCommand;
import net.casesr.recipeapp.domain.Recipe;

public interface RecipeService {
	
	Set<Recipe> getRecipes();

	Recipe findById(String id);

	void deleteById(String id);

	RecipeCommand findCommandById(String id);

	RecipeCommand saveRecipeCommand(RecipeCommand command);

}
