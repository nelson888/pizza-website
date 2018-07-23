package com.tambapps.website.model.request;

import com.tambapps.website.model.user.UserRoleName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class RoleRequest {

  @NotNull
  @PositiveOrZero
  private Long userId;
  @NotNull
  private UserRoleName roleName;

}
