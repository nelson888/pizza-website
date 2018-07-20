package com.tambapps.website.controller;

import com.tambapps.website.model.Order;
import com.tambapps.website.model.User;
import com.tambapps.website.repository.OrderRepository;
import com.tambapps.website.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;

  public OrderController(OrderRepository orderRepository,
      UserRepository userRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  @GetMapping("/{id}")
  public Order getById(@PathVariable("id") Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  @GetMapping("/byUser/{userId}")
  public List<Order> findByUser(@PathVariable("userId") Long id) {
    return orderRepository.findByUser(User.withId(id));
  }

  @PostMapping
  public ResponseEntity<String> createOrder(Long userId, Order order) {
    if (userId == null) {
      return ResponseEntity.badRequest().body("The userId mustn't be null");
    }
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return ResponseEntity.badRequest().body("There is no user with id " + userId);
    }
    order.setUser(user);
    orderRepository.save(order);
    return ResponseEntity.status(HttpStatus.CREATED).build();

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
