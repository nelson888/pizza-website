package com.tambapps.website.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "customer")
public class Customer extends User {

  @OneToOne
  @JoinColumn(name = "address_id")
  private Address address;

}
