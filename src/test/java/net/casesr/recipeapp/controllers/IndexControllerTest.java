package net.casesr.recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import net.casesr.recipeapp.domain.Recipe;
import net.casesr.recipeapp.services.RecipeService;

class IndexControllerTest {
	
	IndexController indexController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		indexController = new IndexController(recipeService);
	}
	
	@Test
	void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}

	@Test
	void testGetIndexPage() {
		// given
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(new Recipe());
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipesData.add(recipe);
		
		when(recipeService.getRecipes()).thenReturn(recipesData);
		
		ArgumentCaptor<Set<Recipe>> argCaptor = ArgumentCaptor.forClass(Set.class);
		
		String expectedView = "index";
		
		assertEquals(expectedView, indexController.getIndexPage(model));
		
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argCaptor.capture());
		Set<Recipe> setOnController = argCaptor.getValue();
		assertEquals(2, setOnController.size());
	}

}
