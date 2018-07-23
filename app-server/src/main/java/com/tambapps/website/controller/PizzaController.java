package com.tambapps.website.controller;

import com.tambapps.website.exception.ResourceNotFoundException;
import com.tambapps.website.model.food.Pizza;
import com.tambapps.website.model.payload.ApiResponse;
import com.tambapps.website.model.user.User;
import com.tambapps.website.model.user.UserDetailsImpl;
import com.tambapps.website.repository.IngredientRepository;
import com.tambapps.website.repository.PizzaRepository;
import com.tambapps.website.repository.UserRepository;
import com.tambapps.website.security.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

  private final PizzaRepository pizzaRepository;
  private final UserRepository userRepository;

  public PizzaController(PizzaRepository pizzaRepository, UserRepository userRepository) {
    this.pizzaRepository = pizzaRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/actives")
  public List<Pizza> allActive() {
    return pizzaRepository.findAllByActiveTrue();
  }

  @GetMapping("/byIngredients") //containing at least one of the ingredients provided
  public List<Pizza> pizzaContainingIngredients(@RequestParam("id") List<Long> ingredientIds) {
    return pizzaRepository.findAllActiveContainingIngredients(ingredientIds);
  }

  @PostMapping
  @PreAuthorize("hasRole('COOK')")
  public ResponseEntity<?> newPizza(@CurrentUser UserDetailsImpl currentUser, @Valid @RequestBody Pizza pizza) {
    pizza.setId(null); //to force auto-increment
    User user = userRepository.findById(currentUser.getId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
    pizza.setAuthor(user);
    pizzaRepository.save(pizza);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{pizzaId}")
        .buildAndExpand(pizza.getId()).toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "Pizza Created Successfully"));
  }
}
