package com.tambapps.website.model.user;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
@RequiredArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    @NonNull
    private UserRoleName name;

    public UserRole(String name){
        this(UserRoleName.valueOf(name));
    }

}
