package net.casesr.recipeapp.domain;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Ingredient {
	
	private String id = UUID.randomUUID().toString();
	private String description;
	private BigDecimal amount;

	@DBRef
	private UnitOfMeasure uom;
	private Recipe recipe;
	
	public Ingredient() {}
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
	}

}
