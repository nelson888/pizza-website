package com.tambapps.website.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue
  @Column(name = "address_id")
  private Long id;
  @NonNull
  @Pattern(regexp = User.ALPHABETIC_REGEX)
  private String state;
  @Column(name = "postal_code")
  @NonNull
  @Positive
  private Integer postalCode;
  @NonNull
  @Pattern(regexp = User.ALPHABETIC_REGEX)
  private String city;
  @NonNull
  @NotBlank
  private String street; //ex: 2 rue blabla

  public static Address copy(Address address) throws NullPointerException {
    return new Address(address.state, address.postalCode, address.city, address.street);
  }

  //constructor to check npo when retrieving address from json
  private Address(Address address) {
    this(address.state, address.postalCode, address.city, address.street);
  }
}
