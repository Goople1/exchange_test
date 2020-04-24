package com.examplesb.demosb.util.mapper;

import com.examplesb.demosb.model.entity.TypeMoniesEntity;
import com.examplesb.demosb.model.response.TypeMoniesDto;
import org.mapstruct.Mapper;

@Mapper
public interface ExchangeMapper {
  TypeMoniesDto typeMoniesDto(TypeMoniesEntity cllient);
}
