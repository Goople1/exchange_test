package com.examplesb.demosb.config;

import com.examplesb.demosb.business.exchange.ExchangeMaintenance;
import com.examplesb.demosb.business.exchange.ExchangeOperation;
import com.examplesb.demosb.business.login.LoginMaintenance;
import com.examplesb.demosb.business.login.LoginOperation;
import com.examplesb.demosb.business.monies.MoniesMaintenance;
import com.examplesb.demosb.business.monies.MoniesOperation;
import com.examplesb.demosb.repositories.ExchangeMoniesJpaRepository;
import com.examplesb.demosb.repositories.TypeMoniesJpaRepository;
import com.examplesb.demosb.util.mapper.ExchangeMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

/**
 * <b>Class</b>: CoreConfig<br/>
 *
 * @author TestUser <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>TestConfig</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>03/04/2020 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
@Lazy
@Configuration
public class CoreConfig {

  @Autowired
  private MessageCustomize messageCustomize;

  @Autowired
  ResourceOwnerPasswordResourceDetails resource;

  @Bean
  public LoginOperation buildOperationMaintenance() {
    return LoginMaintenance.builder()
        .accessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider())
        .accessTokenRequest(new DefaultAccessTokenRequest())
        .resource(resource)
        .build();
  }

  @Bean
  public MoniesOperation buildMoniesOperation(TypeMoniesJpaRepository typeMoniesJpaRepository) {
    return MoniesMaintenance.builder()
        .exchangeMapper(Mappers.getMapper(ExchangeMapper.class))
        .typeMoniesJpaRepository(typeMoniesJpaRepository)
        .build();
  }

  @Bean
  public ExchangeOperation buildExchangeOperation(ExchangeMoniesJpaRepository exchangeMoniesJpaRepository,
                                                  TypeMoniesJpaRepository typeMoniesJpaRepository) {
    return ExchangeMaintenance.builder()
        .exchangeMoniesJpaRepository(exchangeMoniesJpaRepository)
        .typeMoniesJpaRepository(typeMoniesJpaRepository)
        .messageCustomize(messageCustomize)
        .build();
  }

}
