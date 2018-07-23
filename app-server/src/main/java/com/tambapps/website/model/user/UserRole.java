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

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    @Id
    @NonNull
    private UserRoleName name;

    public UserRole(String name){
        this(UserRoleName.valueOf(name));
    }

}
