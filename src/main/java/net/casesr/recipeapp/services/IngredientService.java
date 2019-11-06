package net.casesr.recipeapp.services;

import net.casesr.recipeapp.commands.IngredientCommand;
import net.casesr.recipeapp.domain.Recipe;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(String recipeId, String idToDelete);

}
