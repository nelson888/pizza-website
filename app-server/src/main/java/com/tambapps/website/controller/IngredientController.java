package com.tambapps.website.controller;

import com.tambapps.website.model.food.Ingredient;
import com.tambapps.website.model.payload.ApiResponse;
import com.tambapps.website.repository.IngredientRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

  private final IngredientRepository ingredientRepository;

  public IngredientController(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @GetMapping("")
  public List<Ingredient> getAllIngredients() {
    return ingredientRepository.findAll();
  }

  @GetMapping("/{id}")
  public Ingredient byId(@PathVariable("id") Long id) {
    return ingredientRepository.findById(id).orElse(null);
  }

  @PostMapping
  @PreAuthorize("hasRole('COOK')")
  public ResponseEntity<?> newIngredient(@Valid @RequestBody Ingredient ingredient) {
    ingredient.setId(null); //to force auto-increment
    ingredientRepository.save(ingredient);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{pizzaId}")
        .buildAndExpand(ingredient.getId()).toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "Pizza Created Successfully"));
  }
}
