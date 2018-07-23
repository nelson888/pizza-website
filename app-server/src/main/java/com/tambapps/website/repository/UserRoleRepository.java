package com.tambapps.website.repository;

import com.tambapps.website.model.user.UserRole;
import com.tambapps.website.model.user.UserRoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
  Optional<UserRole> findByName(UserRoleName name);
}
