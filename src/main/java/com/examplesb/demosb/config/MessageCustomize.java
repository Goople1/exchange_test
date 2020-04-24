package com.examplesb.demosb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
@ConfigurationProperties(prefix = "application.parameter.message")
@Getter
@Setter
public class MessageCustomize {
  private String correcto;
  private String errorGenerico;
}
