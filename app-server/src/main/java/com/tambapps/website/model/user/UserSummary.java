package com.tambapps.website.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSummary {

    private Long id;
    private String email;
    private String username;

}
