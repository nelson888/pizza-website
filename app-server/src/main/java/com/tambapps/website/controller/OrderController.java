package com.tambapps.website.controller;

import com.tambapps.website.exception.BadRequestException;
import com.tambapps.website.exception.ForbiddenActionException;
import com.tambapps.website.model.Order;
import com.tambapps.website.model.user.User;
import com.tambapps.website.model.user.UserDetailsImpl;
import com.tambapps.website.model.food.Pizza;
import com.tambapps.website.model.payload.ApiResponse;
import com.tambapps.website.model.request.OrderRequest;
import com.tambapps.website.repository.OrderRepository;
import com.tambapps.website.repository.PizzaRepository;
import com.tambapps.website.repository.UserRepository;
import com.tambapps.website.security.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final PizzaRepository pizzaRepository;

  public OrderController(OrderRepository orderRepository,
                         UserRepository userRepository, PizzaRepository pizzaRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.pizzaRepository = pizzaRepository;
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  public List<Order> getAll() { //TODO use page??
    return orderRepository.findAll();
  }

  @GetMapping("/mine")
  @PreAuthorize("hasRole('USER')")
  public List<Order> findByUser(@CurrentUser UserDetailsImpl currentUser) {
    return orderRepository.findByUser(currentUser.getId());
  }

  @DeleteMapping("/{orderId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> deleteById(@CurrentUser UserDetailsImpl currentUser, @PathVariable("orderId") Long orderId) {
    Order order = orderRepository.findById(orderId).orElse(null);
    if (order == null || !Objects.equals(currentUser.getId(), order.getUser().getId())) {
      throw new ForbiddenActionException("You aren't the owner of this order");
    }
    orderRepository.delete(order);
    return ResponseEntity.ok().build();
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> createOrder(@CurrentUser UserDetailsImpl currentUser,@Valid OrderRequest orderRequest) {
    User user = userRepository.findById(currentUser.getId()).orElseThrow(() ->
    new BadRequestException("There is no user with id " + currentUser.getId()));

    List<Pizza> pizzas = pizzaRepository.findAllById(orderRequest.getPizzaIds());
    if (pizzas.isEmpty()) {
      throw new BadRequestException("You must at least choose one pizza");
    }
    Order order = new Order(orderRequest.getType(), user, pizzas);
    orderRepository.save(order);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{orderId}")
            .buildAndExpand(order.getId()).toUri();

    return ResponseEntity.created(location)
            .body(new ApiResponse(true, "Order Created Successfully"));

  }

  @DeleteMapping
  public ResponseEntity<String> deleteOrder(@RequestBody Long orderId) {
    if (!orderRepository.existsById(orderId)) {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<String> updateOrder(@RequestBody Order order) {
    if (!order.isValid()) {
      return ResponseEntity.badRequest().header("application/json;charset=UTF-8").body("At least one field is missing");
    }
    if (!orderRepository.existsById(order.getId())) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).header("application/json;charset=UTF-8").build();
    }
    try {
      orderRepository.save(order);
    } catch (Exception e) {
      System.err.println("exception");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    return ResponseEntity.ok().build();
  }

}
