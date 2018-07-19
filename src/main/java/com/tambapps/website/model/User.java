package com.tambapps.website.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue
  private Long id;
  private String email;
  private String password;
  private String name;
  @Column(name = "last_name")
  private String lastName;

}
