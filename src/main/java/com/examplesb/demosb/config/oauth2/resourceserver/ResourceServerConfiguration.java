package com.examplesb.demosb.config.oauth2.resourceserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
@Lazy
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()

        .authorizeRequests()
        .antMatchers(this.getSwaggerURLs()).permitAll()
        .antMatchers("/channel/exchange/v1/login").permitAll()
        //.antMatchers("/h2-console/**").permitAll()
        .anyRequest().authenticated();

  }

  private static String[] getSwaggerURLs() {
    return new String[] {
        "/v2/api-docs",
        "/configuration/ui",
        "/swagger-resources",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/swagger-resources/configuration/ui",
        "/swagger-ui.html",
        "/canal/client/v1/swagger/swagger2.json",
        "/swagger-resources/configuration/security",
        "/h2-console/**"};
  }

}
