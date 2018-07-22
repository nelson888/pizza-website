package com.tambapps.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSummary {

    private Long id;
    private String email;
    private String lastName;
    private String name;
}
