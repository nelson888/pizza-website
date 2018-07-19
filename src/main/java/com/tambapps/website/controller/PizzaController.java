package com.tambapps.website.controller;

import com.tambapps.website.model.food.Ingredient;
import com.tambapps.website.model.food.Pizza;
import com.tambapps.website.repository.IngredientRepository;
import com.tambapps.website.repository.PizzaRepository;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

  private final PizzaRepository pizzaRepository;
  private final IngredientRepository ingredientRepository;

  public PizzaController(PizzaRepository pizzaRepository,
      IngredientRepository ingredientRepository) {
    this.pizzaRepository = pizzaRepository;
    this.ingredientRepository = ingredientRepository;
  }

  @GetMapping("/")
  public List<Pizza> getAllPizza() {
    return pizzaRepository.findAll();
  }

  @GetMapping("/actives")
  public List<Pizza> allActive() {
    Pizza pizza = new Pizza();
    return pizzaRepository.findAll(Example.of(pizza));
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public Pizza allActive(@PathVariable("id") Long id) {
    return pizzaRepository.findById(id).orElse(null);
  }
/*
  @GetMapping("/byIngredients")
  public List<Pizza> pizzaByIngredient(@RequestBody List<Long> ingredientIds) {
    return pizzaRepository.findAllByIngredientsContaining(new HashSet<>(ingredientRepository.findAllById(ingredientIds)));
  }

   @GetMapping("/byIngredient")
  public List<Pizza> pizzaByIngredient(@RequestBody Long ingredientId) {
    return pizzaRepository.findAllByIngredientsContaining(ingredientId);
  }

*/

  @GetMapping("/byIngredientTest")
  public List<Pizza> pizzaByIngredient() {
    return pizzaRepository.findAllByIngredientsContaining(1L);
  }
}
