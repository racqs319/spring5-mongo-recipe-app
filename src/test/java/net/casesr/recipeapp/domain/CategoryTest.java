package net.casesr.recipeapp.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {
	
	Category category;

	@BeforeEach
	void setUp() throws Exception {
		category = new Category();
	}

	@Test
	void testGetId() {
		Long idValue = 4L;
		
		category.setId(idValue);
		
		assertEquals(idValue, category.getId());
	}

	@Test
	void testGetDescription() {
		//fail("Not yet implemented");
	}

	@Test
	void testGetRecipes() {
		//fail("Not yet implemented");
	}

}
