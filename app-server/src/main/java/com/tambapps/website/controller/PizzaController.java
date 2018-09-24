package com.tambapps.website.controller;

import com.tambapps.website.exception.ResourceNotFoundException;
import com.tambapps.website.model.food.Pizza;
import com.tambapps.website.model.payload.ApiResponse;
import com.tambapps.website.model.user.User;
import com.tambapps.website.model.user.UserDetailsImpl;
import com.tambapps.website.repository.PizzaRepository;
import com.tambapps.website.repository.UserRepository;
import com.tambapps.website.security.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

  private final PizzaRepository pizzaRepository;
  private final UserRepository userRepository;

  public PizzaController(PizzaRepository pizzaRepository, UserRepository userRepository) {
    this.pizzaRepository = pizzaRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("")
  public List<Pizza> all(@RequestParam("query") String query) {
    if (query == null || query.isEmpty()) {
      return pizzaRepository.findAll();
    } else {
      return pizzaRepository.findAllByNameStartingWith(query);
    }
  }

  @GetMapping("/byIngredient/{id}")
  public List<Pizza> byIngredient(@PathVariable("id") Long id) {
    return pizzaRepository.findAllByIngredientsContaining(id);
  }

  @PostMapping
  //@PreAuthorize("hasRole('COOK')")
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
