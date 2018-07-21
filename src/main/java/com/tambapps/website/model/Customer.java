package com.tambapps.website.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "customer")
public class Customer extends User {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  @NonNull
  @NotNull
  @Valid
  private Address address;

  public static Customer copy(Customer customer) throws NullPointerException {
    return new Customer(customer.email, customer.password, customer.name, customer.lastName,
            Address.copy(customer.address));
  }

  //constructor to check npo when retrieving address from json
  private Customer(String email, String password, String name, String lastName, Address address) {
    super(email, password, name, lastName);
    this.address = address;
  }
}
