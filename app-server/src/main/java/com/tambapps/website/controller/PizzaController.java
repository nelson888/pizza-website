package com.tambapps.website.controller;

import com.tambapps.website.exception.ResourceNotFoundException;
import com.tambapps.website.model.food.Pizza;
import com.tambapps.website.repository.IngredientRepository;
import com.tambapps.website.repository.PizzaRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  public List<Pizza> getAllPizzas() {
    return pizzaRepository.findAll();
  }

  @GetMapping("/actives")
  public List<Pizza> allActive() {
    return pizzaRepository.findAllByActiveTrue();
  }

  @GetMapping("/{id}")
  public Pizza allActive(@PathVariable("id") Long id) { //TODO check if pizza is active because not everyone can check not active pizzas
    return pizzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pizza", "id", id));
  }

  @GetMapping("/byIngredients") //containing at least one of the ingredients provided
  public List<Pizza> pizzaContainingIngredients(@RequestParam("id") List<Long> ingredientIds) {
    return pizzaRepository.findAllContainingIngredients(ingredientIds);
  }

}
