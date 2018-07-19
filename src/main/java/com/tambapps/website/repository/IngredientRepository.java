package com.tambapps.website.repository;

import com.tambapps.website.model.food.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
