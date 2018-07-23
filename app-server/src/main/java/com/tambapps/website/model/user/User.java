package com.tambapps.website.model.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = {"email"})}) //TODO to implement in sql script
public class User {

  //TODO add '-' and character like 'é', 'è', ...
  public static final String ALPHABETIC_REGEX = "[a-zA-Z]+";

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

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<UserRole> roles = new HashSet<>();

  public User(){}

}
