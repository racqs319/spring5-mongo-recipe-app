package net.casesr.recipeapp.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import net.casesr.recipeapp.bootstrap.RecipeBootstrap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.casesr.recipeapp.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataMongoTest
class UnitOfMeasureRepositoryIT {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	RecipeRepository recipeRepository;

	@BeforeEach
	void setUp() throws Exception {
		recipeRepository.deleteAll();
		unitOfMeasureRepository.deleteAll();
		categoryRepository.deleteAll();

		RecipeBootstrap recipeBootstrap = new RecipeBootstrap(categoryRepository,
				unitOfMeasureRepository, recipeRepository);
		recipeBootstrap.onApplicationEvent(null);
	}

	@Test
	void testFindByDescription() {
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		assertEquals("Teaspoon", uomOptional.get().getDescription());
	}
	
	@Test
	void testFindByDescriptionCup() {
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");
		
		assertEquals("Cup", uomOptional.get().getDescription());
	}

}
