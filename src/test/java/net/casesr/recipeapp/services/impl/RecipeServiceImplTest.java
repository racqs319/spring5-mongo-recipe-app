package net.casesr.recipeapp.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import net.casesr.recipeapp.commands.RecipeCommand;
import net.casesr.recipeapp.converters.RecipeCommandToRecipe;
import net.casesr.recipeapp.converters.RecipeToRecipeCommand;
import net.casesr.recipeapp.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.casesr.recipeapp.domain.Recipe;
import net.casesr.recipeapp.repositories.RecipeRepository;

class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
		
	}

	@SuppressWarnings("rawtypes")
	@Test
	void testGetRecipes() {
		Recipe recipe = new Recipe();
		HashSet recipesData = new HashSet<>();
		recipesData.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipesData);
		
		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipesData.size(), recipes.size());
		
		verify(recipeRepository, times(1)).findAll();
	}

	@Test
	void testGetRecipeById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		Recipe recipeReturned = recipeService.findById(1L);

		assertNotNull(recipeReturned,"Null recipe returned");
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test
	public void testGetRecipeByIdNotFound() throws Exception {
		Optional<Recipe> recipeOptional = Optional.empty();

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		Throwable exception = assertThrows(NotFoundException.class, () -> {
			recipeService.findById(1L);
		});

		assertEquals("Recipe not found for ID value: 1", exception.getMessage());

		//should go boom
	}

	@Test
	public void testGetRecipeCommandById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);

		when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

		RecipeCommand commandById = recipeService.findCommandById(1L);

		assertNotNull(commandById, "Null recipe returned");
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test
	public void testDeleteById() throws Exception {
		Long idToDelete = Long.valueOf(2L);
		recipeService.deleteById(idToDelete);

		verify(recipeRepository, times(1)).deleteById(anyLong());
	}

}
