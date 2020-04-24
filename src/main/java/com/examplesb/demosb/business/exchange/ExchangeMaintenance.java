package com.examplesb.demosb.business.exchange;

import com.examplesb.demosb.config.MessageCustomize;
import com.examplesb.demosb.model.entity.ExchangeMoniesEntity;
import com.examplesb.demosb.model.request.ExchangeRequest;
import com.examplesb.demosb.model.response.ExchangeDto;
import com.examplesb.demosb.model.response.ExchangeTransactionDto;
import com.examplesb.demosb.repositories.ExchangeMoniesJpaRepository;
import com.examplesb.demosb.repositories.TypeMoniesJpaRepository;
import com.examplesb.demosb.util.Constantes;
import io.reactivex.Single;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@Builder
public class ExchangeMaintenance implements ExchangeOperation<ExchangeRequest> {
  private ExchangeMoniesJpaRepository exchangeMoniesJpaRepository;
  private TypeMoniesJpaRepository typeMoniesJpaRepository;
  private MessageCustomize messageCustomize;

  @Override
  public Single getExchange(ExchangeRequest exchangeRequest) {
    return Single.fromCallable(() -> {
      ExchangeMoniesEntity actual = exchangeMoniesJpaRepository.findByFromTo(exchangeRequest.getFrom(), exchangeRequest.getTo());
      String rate = Constantes.VACIO;
      if (validateFromOrigen(exchangeRequest, actual)) {
        rate = actual.getRateOne();
      } else {
        if (validateFromDestiny(exchangeRequest, actual)) {
          rate = actual.getRateTwo();
        }
      }
      return new ResponseEntity(
          rate
          , HttpStatus.OK);
    });
  }

  @Override
  public Single updateExchange(ExchangeRequest exchangeRequest) {
    return Single.fromCallable(() -> {
      ExchangeMoniesEntity actual = exchangeMoniesJpaRepository.findByFromTo(exchangeRequest.getFrom(), exchangeRequest.getTo());

      validateExchange(actual);

      updateRates(actual, exchangeRequest);

      exchangeMoniesJpaRepository.saveAndFlush(actual);

      return new ResponseEntity(
          ExchangeDto.builder()
              .mensaje(messageCustomize.getCorrecto())
              .build()
          , HttpStatus.OK);
    });
  }

  private void updateRates(ExchangeMoniesEntity actual, ExchangeRequest exchangeRequest) {
    if (validateFromOrigen(exchangeRequest, actual)) {
      actual.setRateOne(exchangeRequest.getRate().toString());
      actual.setRateTwo(getNewRate(exchangeRequest.getRate()));
    } else {
      if (exchangeRequest.getFrom().equals(actual.getFromTwo()) &&
          exchangeRequest.getTo().equals(actual.getToTwo())) {
        actual.setRateTwo(exchangeRequest.getRate().toString());
        actual.setRateOne(getNewRate(exchangeRequest.getRate()));
      }
    }
  }

  private String getNewRate(BigDecimal rate) {
    return BigDecimal.ONE.divide(rate, 20, RoundingMode.HALF_UP).toString();
  }

  private void validateExchange(ExchangeMoniesEntity exchange) {
    if (Objects.isNull(exchange)) {
      throw new RuntimeException(messageCustomize.getErrorGenerico());
    }
  }

  @Override
  public Single executeExchange(ExchangeRequest exchangeTransactionRequest) {
    return Single.fromCallable(() -> {
      ExchangeMoniesEntity exchange = exchangeMoniesJpaRepository.findByFromTo(
          exchangeTransactionRequest.getFrom(), exchangeTransactionRequest.getTo());

      validateExchange(exchange);
      return new ResponseEntity(buildExchangeResponse(exchangeTransactionRequest, exchange), HttpStatus.OK);

    });
  }

  private boolean validateFromOrigen(ExchangeRequest exchangeRequest, ExchangeMoniesEntity exchange) {
    return exchangeRequest.getFrom().equals(exchange.getFromOne()) &&
        exchangeRequest.getTo().equals(exchange.getToOne());
  }

  private boolean validateFromDestiny(ExchangeRequest exchangeRequest, ExchangeMoniesEntity exchange) {
    return exchangeRequest.getFrom().equals(exchange.getFromTwo()) &&
        exchangeRequest.getTo().equals(exchange.getToTwo());
  }

  private ExchangeTransactionDto buildExchangeResponse(ExchangeRequest exchangeRequest,
                                                       ExchangeMoniesEntity exchange) {
    BigDecimal rate = BigDecimal.ONE;
    String typeChange = Constantes.VACIO;
    ExchangeTransactionDto exchangeResponse = new ExchangeTransactionDto();
    exchangeResponse.setMoneyOrigen(exchangeRequest.getFrom());
    exchangeResponse.setMoneyDestiny(exchangeRequest.getTo());
    exchangeResponse.setAmount(exchangeRequest.getRate());

    if (validateFromOrigen(exchangeRequest, exchange)) {
      rate = new BigDecimal(exchange.getRateOne());
      typeChange = exchange.getRateOne();

      exchangeResponse.setSymbol(typeMoniesJpaRepository.findById(exchangeRequest.getTo())
          .get().getSymbol());
    } else {
      if (validateFromDestiny(exchangeRequest, exchange)) {
        rate = new BigDecimal(exchange.getRateTwo());
        typeChange = exchange.getRateTwo();

        exchangeResponse.setSymbol(typeMoniesJpaRepository.findById(exchangeRequest.getFrom())
            .get().getSymbol());
      }
    }

    exchangeResponse.setAmountUpdate(exchangeRequest.getRate()
        .multiply(rate));
    exchangeResponse.setTypeExchange(typeChange);

    return exchangeResponse;
  }
}
