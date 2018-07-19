package com.tambapps.website.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue
  private Long id;
  private String state;
  @Column(name = "postal_code")
  private int postalCode;
  private String city;
  private String street; //ex: 2 rue blabla

}
