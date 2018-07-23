package com.tambapps.website.repository;

import com.tambapps.website.model.Address;
import com.tambapps.website.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
  List<Address> findByUser(User user);
}
