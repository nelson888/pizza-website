package com.tambapps.website.repository;

import com.tambapps.website.model.food.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
  @Query(value = "select * from pizza WHERE pizza.id IN (select pizza_id from pizza_ingredient WHERE ingredient_id = ?1)", nativeQuery = true)
  List<Pizza> findAllByIngredientsContaining(Long id);
}
