package com.tambapps.website.model;

import com.tambapps.website.model.food.Pizza;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "order_table")
public class Order {

  @Id
  @GeneratedValue
  @Column(name = "order_id")
  private Long id;
  @Enumerated(EnumType.STRING)
  private OrderingType type;
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "order_pizza", joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "pizza_id"))
  private List<Pizza> pizzas;

  public boolean isValid() {
    return id != null && type != null && user != null && pizzas != null;
  }
}
