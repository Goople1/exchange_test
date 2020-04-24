package com.examplesb.demosb.business.monies;

import com.examplesb.demosb.model.entity.TypeMoniesEntity;
import com.examplesb.demosb.model.response.LoginDto;
import com.examplesb.demosb.model.response.TypeMoniesDto;
import com.examplesb.demosb.repositories.TypeMoniesJpaRepository;
import com.examplesb.demosb.util.mapper.ExchangeMapper;
import io.reactivex.Flowable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
public class MoniesMaintenance implements MoniesOperation<ResponseEntity> {
  private TypeMoniesJpaRepository typeMoniesJpaRepository;
  private ExchangeMapper exchangeMapper;

  @Override
  public Flowable<ResponseEntity> listMonies() {
    return Flowable.fromCallable(() -> {
      List<TypeMoniesEntity> listTypeMonies = typeMoniesJpaRepository.findAll();
      List<TypeMoniesDto> typeMoniesDto = listTypeMonies.stream().map(mon ->
          exchangeMapper.typeMoniesDto(mon)).collect(Collectors.toList());
      return new ResponseEntity(
          typeMoniesDto
          , HttpStatus.OK);
    });
  }
}
