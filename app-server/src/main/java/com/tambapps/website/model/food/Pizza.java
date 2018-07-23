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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
@Entity
@Table(name = "pizza")
public class Pizza {
  @Id
  @GeneratedValue
  @Column(name = "pizza_id")
  private Long id;
  @NotNull
  private String name;
  @Positive
  private int price;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id"),
      inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  @NotNull
  private Set<Ingredient> ingredients;
  @NotNull
  private Boolean active;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id")
  private User author;

}
