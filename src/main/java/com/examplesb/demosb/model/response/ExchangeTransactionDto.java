package com.examplesb.demosb.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Respuesta de conversión de moneda")
public class ExchangeTransactionDto {
  @ApiModelProperty(name = "amount",
      value = "Monto", example = "10",
      dataType = "BigDecimal", position = 1)
  private BigDecimal amount;
  @ApiModelProperty(name = "amountUpdate",
      value = "Monto Actualizado", example = "37",
      dataType = "BigDecimal", position = 2)
  private BigDecimal amountUpdate;
  @ApiModelProperty(name = "moneyOrigen",
      value = "Moneda Origen", example = "USD",
      dataType = "String", position = 3)
  private String moneyOrigen;
  @ApiModelProperty(name = "moneyDestiny",
      value = "Moneda Destino", example = "PEN",
      dataType = "String", position = 4)
  private String moneyDestiny;
  @ApiModelProperty(name = "typeExchange",
      value = "Tipo de cambio", example = "3.37",
      dataType = "String", position = 4)
  private String typeExchange;

  @ApiModelProperty(name = "typeExchange",
      value = "Simbolo de moneda", example = "S/.",
      dataType = "String", position = 5)
  private String symbol;
}
