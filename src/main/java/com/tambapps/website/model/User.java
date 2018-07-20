package com.tambapps.website.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Data
@Entity
@RequiredArgsConstructor(staticName = "withId")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue
  @NonNull
  @Column(name = "user_id")
  private Long id;
  private String email;
  private String password;
  private String name;
  @Column(name = "last_name")
  private String lastName;

}
