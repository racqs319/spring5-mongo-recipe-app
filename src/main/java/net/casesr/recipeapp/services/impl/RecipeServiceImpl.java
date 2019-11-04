package net.casesr.recipeapp.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import net.casesr.recipeapp.commands.RecipeCommand;
import net.casesr.recipeapp.converters.RecipeCommandToRecipe;
import net.casesr.recipeapp.converters.RecipeToRecipeCommand;
import net.casesr.recipeapp.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.casesr.recipeapp.domain.Recipe;
import net.casesr.recipeapp.repositories.RecipeRepository;
import net.casesr.recipeapp.services.RecipeService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository,
							 RecipeCommandToRecipe recipeCommandToRecipe,
							 RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("In RecipeServiceImpl.getRecipes(");
		
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		
		return recipeSet;
	}

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
	public Recipe findById(Long id) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(id);

		if (!recipeOptional.isPresent()) {
			throw new NotFoundException("Recipe not found for ID value: " + id);
		}

		return recipeOptional.get();
	}

	@Transactional
	@Override
	public RecipeCommand findCommandById(Long id) {
		return recipeToRecipeCommand.convert(findById(id));
	}

	@Transactional
	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug("Saved Recipe Id: " + savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}
}
