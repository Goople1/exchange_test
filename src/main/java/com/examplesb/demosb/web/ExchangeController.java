package com.examplesb.demosb.web;


import static io.reactivex.schedulers.Schedulers.io;


import com.examplesb.demosb.business.exchange.ExchangeOperation;
import com.examplesb.demosb.business.monies.MoniesOperation;
import com.examplesb.demosb.model.request.ExchangeRequest;
import com.examplesb.demosb.model.response.ExchangeDto;
import com.examplesb.demosb.model.response.ExchangeTransactionDto;
import com.examplesb.demosb.model.response.TypeMoniesDto;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;
import java.security.Principal;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("${application.client.api.path}" +
    "${application.client.api.pathExchange}")
@Api(value = "ExchangeController",
    tags = {"Exchange Controller"})
@Slf4j
@RestController
public class ExchangeController {

  private MoniesOperation moniesOperation;
  private ExchangeOperation exchangeOperation;

  public ExchangeController(MoniesOperation moniesOperation, ExchangeOperation exchangeOperation) {
    this.moniesOperation = moniesOperation;
    this.exchangeOperation = exchangeOperation;
  }

  @GetMapping(value = "/monies", produces =
      MediaType.APPLICATION_STREAM_JSON_VALUE)
  @ApiOperation(
      value = "Listar los tipo de moneda.",
      produces = MediaType.APPLICATION_STREAM_JSON_VALUE,
      httpMethod = "GET",
      notes = "classpath:swagger/notes/list-monies.md")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Se obtuvo la lista de monedas.",
      response = TypeMoniesDto.class, responseHeaders = {})})
  public Flowable<ResponseEntity> listMonies(Principal principal) {
    log.info("listMonies");
    return moniesOperation.listMonies()
        .subscribeOn(io());
  }

  @GetMapping(value = "/findexchange/{from}/{to}", produces =
      MediaType.APPLICATION_STREAM_JSON_VALUE)
  @ApiOperation(
      value = "Obtener Rate del cambio de moneda.",
      produces = MediaType.APPLICATION_STREAM_JSON_VALUE,
      httpMethod = "GET",
      notes = "classpath:swagger/notes/find-exchange.md")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Se obtuvo rate correctamente.",
      response = BigDecimal.class, responseHeaders = {})})
  public Single<ResponseEntity> getExchange(
      Principal principal,
      @ApiParam(value = "Origen Moneda",
          required = true)
      @NotNull
      @PathVariable String from,
      @ApiParam(value = "Destino Moneda",
          required = true)
      @NotNull
      @PathVariable
          String to) {
    log.info("getExchange");
    ExchangeRequest exchangeRequest = new ExchangeRequest();
    exchangeRequest.setFrom(from);
    exchangeRequest.setTo(to);
    return exchangeOperation.getExchange(exchangeRequest)
        .subscribeOn(io());
  }

  @PostMapping(value = "/updateexchange", produces =
      MediaType.APPLICATION_STREAM_JSON_VALUE)
  @ApiOperation(
      value = "Actualizar el cambio de moneda.",
      produces = MediaType.APPLICATION_STREAM_JSON_VALUE,
      httpMethod = "POST",
      notes = "classpath:swagger/notes/update-exchange.md")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Se actualizo correctamente.",
      response = ExchangeDto.class, responseHeaders = {})})
  public Single<ResponseEntity> updateTypeExchange(Principal principal,
                                                   @RequestBody
                                                       ExchangeRequest exchangeRequest) {
    log.info("updateTypeExchange");
    return exchangeOperation.updateExchange(exchangeRequest)
        .subscribeOn(io());
  }

  @PostMapping(value = "/executeexchange", produces =
      MediaType.APPLICATION_STREAM_JSON_VALUE)
  @ApiOperation(
      value = "obtener el cambio de moneda.",
      produces = MediaType.APPLICATION_STREAM_JSON_VALUE,
      httpMethod = "POST",
      notes = "classpath:swagger/notes/execute-exchange.md")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Se obtuvo correctamente.",
      response = ExchangeTransactionDto.class, responseHeaders = {})})
  public Single<ResponseEntity> executeExchange(Principal principal,
                                                @RequestBody
                                                    ExchangeRequest exchangeTransactionRequest) {
    log.info("executeExchange");
    return exchangeOperation.executeExchange(exchangeTransactionRequest)
        .subscribeOn(io());
  }
}
