package com.tambapps.website.controller;

import com.tambapps.website.exception.BadRequestException;
import com.tambapps.website.model.user.UserDetailsImpl;
import com.tambapps.website.model.user.UserSummary;
import com.tambapps.website.repository.UserRepository;
import com.tambapps.website.security.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserDetailsImpl currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getEmail(), currentUser.getUsername());
    }

    @GetMapping("/checkEmailAvailability")
    public Boolean checkEmailAvailability(@RequestParam(value = "email") String email) {
        if (email == null) {
            throw new BadRequestException("The request param 'email' is missing");
        }
        return !userRepository.existsByEmail(email);
    }
}
