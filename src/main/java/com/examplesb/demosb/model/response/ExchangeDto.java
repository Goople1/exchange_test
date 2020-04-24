package com.examplesb.demosb.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@ApiModel(description = "Respuesta del registro del cliente")
public class ExchangeDto {
  @ApiModelProperty(name = "mensaje",
      value = "Registro Correcto", example = "Correcto",
      dataType = "String", position = 1)
  private String mensaje;
}
