package com.tambapps.website.controller;


import com.tambapps.website.exception.BadRequestException;
import com.tambapps.website.exception.InternalErrorException;
import com.tambapps.website.exception.ResourceNotFoundException;
import com.tambapps.website.model.food.Pizza;
import com.tambapps.website.model.payload.ApiResponse;
import com.tambapps.website.model.request.RoleRequest;
import com.tambapps.website.model.user.User;
import com.tambapps.website.model.user.UserRoleName;
import com.tambapps.website.repository.PizzaRepository;
import com.tambapps.website.repository.UserRepository;
import com.tambapps.website.repository.UserRoleRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  private final PizzaRepository pizzaRepository;
  private final UserRepository userRepository;
  private final UserRoleRepository roleRepository;

  public AdminController(PizzaRepository pizzaRepository,
      UserRepository userRepository,
      UserRoleRepository roleRepository) {
    this.pizzaRepository = pizzaRepository;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @GetMapping("/pizza/all")
  public List<Pizza> getAllPizzas() {
    return pizzaRepository.findAll();
  }

  @PutMapping("pizza/{pizzaId}/active")
  public ResponseEntity<?> active(@PathVariable("pizzaId") Long pizzaId,
      @RequestBody Boolean active) {
    if (active == null) {
      throw new BadRequestException("parameter 'active' cannot be null");
    }
    Pizza pizza = pizzaRepository.findById(pizzaId)
        .orElseThrow(() -> new ResourceNotFoundException("Pizza", "id", pizzaId));
    pizzaRepository.save(pizza);

    return ResponseEntity.ok().body(new ApiResponse(true, "Pizza updated successfully"));
  }

  @DeleteMapping("pizza/{pizzaId}")
  public ResponseEntity<?> delete(@PathVariable("pizzaId") Long pizzaId) {
    pizzaRepository.deleteById(pizzaId);
    return ResponseEntity.ok().body(new ApiResponse(true, "Order Deleted Successfully"));
  }

  @PutMapping("/user/role")
  public ResponseEntity<?> addRole(@Valid @RequestBody RoleRequest roleRequest) {
    User user = userRepository.findById(roleRequest.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", roleRequest.getUserId()));
    user.getRoles().add(roleRepository.findByName(roleRequest.getRoleName()).orElseThrow(() ->
        new InternalErrorException("Couldn't find role " + roleRequest.getRoleName())));
    userRepository.save(user);

    return ResponseEntity.ok().body(new ApiResponse(true, "Role added successfully"));
  }

  @DeleteMapping("/user/{userId}/{roleName}")
  public ResponseEntity<?> removeRole(@PathVariable("userId") Long userId,
      @PathVariable("roleName") String roleName) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    UserRoleName userRoleName;
    try {
      userRoleName = UserRoleName.valueOf(roleName);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException("There is no role '" + roleName + "'");
    }
    user.getRoles().remove(roleRepository.findByName(userRoleName).orElseThrow(() ->
        new InternalErrorException("Couldn't find role " + userRoleName)));
    userRepository.save(user);

    return ResponseEntity.ok().body(new ApiResponse(true, "Role removed successfully"));
  }

}

