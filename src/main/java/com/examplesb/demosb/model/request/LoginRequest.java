package com.examplesb.demosb.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "Login Request")
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
  @NotNull
  @ApiModelProperty(name = "username",
      value = "usuario del aplicativo", example = "jose",
      dataType = "String", required = true, position = 1)
  private String username;
  @NotNull
  @ApiModelProperty(name = "password",
      value = "password del usuario aplicativo", example = "123456",
      dataType = "String", required = true, position = 2)
  private String password;
}
