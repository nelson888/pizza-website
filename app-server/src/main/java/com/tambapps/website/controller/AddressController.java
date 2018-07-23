package com.tambapps.website.controller;

import com.tambapps.website.exception.ResourceNotFoundException;
import com.tambapps.website.model.Address;
import com.tambapps.website.model.payload.ApiResponse;
import com.tambapps.website.model.user.User;
import com.tambapps.website.model.user.UserDetailsImpl;
import com.tambapps.website.repository.AddressRepository;
import com.tambapps.website.repository.UserRepository;
import com.tambapps.website.security.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

  private final UserRepository userRepository;
  private final AddressRepository addressRepository;

  public AddressController(UserRepository userRepository, AddressRepository addressRepository) {
    this.userRepository = userRepository;
    this.addressRepository = addressRepository;
  }

  @PostMapping
  public ResponseEntity<?> createAddress(@CurrentUser UserDetailsImpl currentUser,
      @Valid Address address) {
    User user = userRepository.findById(currentUser.getId())
        .orElseThrow(() -> new ResourceNotFoundException("user", "id", currentUser.getId()));
    address.setUser(user);

    addressRepository.save(address);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{addressId}")
        .buildAndExpand(address.getId()).toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "Order Created Successfully"));
  }

  @GetMapping
  public List<Address> getAllAdresses(@CurrentUser UserDetailsImpl currentUser) {
    return addressRepository.findByUser(User.withId(currentUser.getId()));
  }
}
