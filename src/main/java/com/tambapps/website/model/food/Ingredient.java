package com.tambapps.website.model.food;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {
  @Id
  @GeneratedValue
  @Column(name = "ingredient_id")
  private Long id;
  private String name;
}
