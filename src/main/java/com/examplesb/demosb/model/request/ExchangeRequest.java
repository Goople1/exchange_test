package com.examplesb.demosb.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Exchange Request")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRequest {
  @NotNull
  @ApiModelProperty(name = "from",
      value = "Tipo de Moneda", example = "USD",
      dataType = "Character", required = true, position = 1)
  private String from;
  @NotNull
  @ApiModelProperty(name = "to",
      value = "Tipo de Moneda", example = "PEN",
      dataType = "Character", required = true, position = 2)
  private String to;

  @NotNull
  @ApiModelProperty(name = "rate",
      value = "Nuevo Rate", example = "5",
      dataType = "BigDecimal", required = true, position = 3)
  private BigDecimal rate;
}
