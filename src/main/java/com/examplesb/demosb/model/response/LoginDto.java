package com.examplesb.demosb.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Respuesta de Login")
@Setter
@Getter
@Builder
public class LoginDto {
  @ApiModelProperty(name = "token",
      value = "Token Generado", example = "eyasdasdsadar",
      dataType = "String", position = 1)
  private String token;
  @ApiModelProperty(name = "time",
      value = "Tiempo de duracion del token", example = "5555",
      dataType = "int", position = 2)
  private int time;
}
