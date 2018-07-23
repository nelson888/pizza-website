package com.tambapps.website.model;

import com.tambapps.website.model.food.Pizza;
import com.tambapps.website.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode()
@Entity
@Table(name = "order_table")
public class Order {

  @Id
  @GeneratedValue
  @Column(name = "order_id")
  private Long id;
  @Enumerated(EnumType.STRING)
  @NonNull
  private OrderingType type;
  @ManyToOne
  @JoinColumn(name = "user_id")
  @NonNull
  private User user;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "order_pizza", joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "pizza_id"))
  @NonNull
  private List<Pizza> pizzas;

  private LocalDateTime creationDate;

  public boolean isValid() {
    return id != null && type != null && user != null && pizzas != null;
  }
}
