package com.examplesb.demosb.config.oauth2.resourceserver;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
@Lazy
public class Oauth2ClientConfiguration {

  @Bean
  @ConfigurationProperties("security.oauth2.client")
  public ResourceOwnerPasswordResourceDetails resourceOwnerPasswordResourceDetails() {
    return new ResourceOwnerPasswordResourceDetails();
  }

  @Bean
  public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext) {
    return new OAuth2RestTemplate(this.resourceOwnerPasswordResourceDetails(), oauth2ClientContext);
  }
}
