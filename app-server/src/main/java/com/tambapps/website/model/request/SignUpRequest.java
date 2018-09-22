package com.tambapps.website.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String lastName;
    private String name;
}
