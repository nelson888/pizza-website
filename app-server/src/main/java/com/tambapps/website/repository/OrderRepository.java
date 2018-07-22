package com.tambapps.website.repository;


import com.tambapps.website.model.Order;
import com.tambapps.website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query(value = "select * from `order` WHERE `order`.order_id IN (select order_id from order_pizza WHERE user_id = ?1)", nativeQuery = true)
  List<Order> findByUser(Long userId);
}
