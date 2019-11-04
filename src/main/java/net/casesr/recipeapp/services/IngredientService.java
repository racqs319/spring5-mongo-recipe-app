package net.casesr.recipeapp.services;

import net.casesr.recipeapp.commands.IngredientCommand;
import net.casesr.recipeapp.domain.Recipe;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(Long recipeId, Long idToDelete);

}
