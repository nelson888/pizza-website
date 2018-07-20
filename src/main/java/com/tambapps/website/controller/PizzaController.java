package com.tambapps.website.controller;

import com.tambapps.website.model.food.Pizza;
import com.tambapps.website.repository.IngredientRepository;
import com.tambapps.website.repository.PizzaRepository;
import org.springframework.data.domain.Example;
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

  @GetMapping("/byIngredients") //containing at least one of the ingredients provided
  public List<Pizza> pizzaContainingIngredients(@RequestParam("id") List<Long> ingredientIds) {
    return pizzaRepository.findAllContainingIngredients(ingredientIds);
  }

}
