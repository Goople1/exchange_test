package com.examplesb.demosb.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Lista de Tipo de Monedas")
@Setter
@Getter
public class TypeMoniesDto {
  @ApiModelProperty(name = "typeAbbr",
      value = "Tipo de Abreviatura de Moneda", example = "PEN",
      dataType = "String", position = 1)
  private String typeAbbr;
  @ApiModelProperty(name = "typeAbbr",
      value = "Simbolo", example = "S/.",
      dataType = "String", position = 2)
  private String symbol;
  @ApiModelProperty(name = "description",
      value = "Moneda Peruana", example = "Nuevo Sol",
      dataType = "String", position = 3)
  private String description;
  @ApiModelProperty(name = "urlImagen",
      value = "Ref. Moneda", example = "google.com/images/234234",
      dataType = "String", position = 4)
  private String urlImagen;
}
