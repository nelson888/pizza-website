package com.tambapps.website.controller;

import com.tambapps.website.model.Customer;
import com.tambapps.website.model.UserRole;
import com.tambapps.website.model.UserRoleName;
import com.tambapps.website.model.payload.ApiResponse;
import com.tambapps.website.model.payload.JwtAuthenticationResponse;
import com.tambapps.website.model.request.LoginRequest;
import com.tambapps.website.repository.CustomerRepository;
import com.tambapps.website.security.JwtTokenProvider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {

        if(customerRepository.existsByEmail(customer.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse(false, "This email address is already used"));
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRoles(Collections.singleton(new UserRole(UserRoleName.USER)));
        Customer result = customerRepository.save(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}