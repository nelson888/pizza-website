package com.tambapps.website.model.request;

import com.tambapps.website.model.Address;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private Address address;
    private String lastName;
    private String name;
}
