package com.tambapps.website.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Data
@Entity
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = {"email"})}) //TODO to implement in sql script
public class User {

  //TODO add '-' and character like 'é', 'è', ...
  static final String ALPHABETIC_REGEX = "[a-zA-Z]+";

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  protected Long id;
  @NonNull
  @Email
  protected String email;
  @NonNull
  @NotBlank
  protected String password;
  @NonNull
  @Pattern(regexp = ALPHABETIC_REGEX)
  protected String name;
  @Column(name = "last_name")
  @NonNull
  @Pattern(regexp = ALPHABETIC_REGEX)
  protected String lastName;

  public User(){}

}
