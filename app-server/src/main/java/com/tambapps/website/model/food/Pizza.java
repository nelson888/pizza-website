package com.tambapps.website.model.food;

import com.tambapps.website.model.user.User;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "pizza")
public class Pizza {
  @Id
  @GeneratedValue
  @Column(name = "pizza_id")
  private Long id;
  private String name;
  private int price;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id"),
      inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private Set<Ingredient> ingredients;
  private Boolean active;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "author_id")
  private User author;

}
