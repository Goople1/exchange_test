package com.examplesb.demosb.business.login;

import com.examplesb.demosb.model.request.LoginRequest;
import com.examplesb.demosb.model.response.LoginDto;
import io.reactivex.Single;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Slf4j
@Builder
public class LoginMaintenance implements LoginOperation<LoginRequest, ResponseEntity> {
  private AccessTokenProvider accessTokenProvider;
  private AccessTokenRequest accessTokenRequest;
  private ResourceOwnerPasswordResourceDetails resource;

  public Single<ResponseEntity> login(LoginRequest loginRequest) {

    log.info("login Cliente");

    return Single.fromCallable(() -> {
      accessTokenRequest.set("username", loginRequest.getUsername());
      accessTokenRequest.set("password", loginRequest.getPassword());
      OAuth2AccessToken oAuth2AccessToken = accessTokenProvider.obtainAccessToken(resource, accessTokenRequest);
      return new ResponseEntity(
          LoginDto.builder()
              .token(oAuth2AccessToken.getValue())
              .time(oAuth2AccessToken.getExpiresIn())
              .build()
          , HttpStatus.OK);
    }).doOnError(e -> log.error("Error createClient {}", e.getMessage()))
        .onErrorResumeNext(
            ex -> Single.error(new RuntimeException("Error Login "
                .concat(ex.getMessage()), ex)));
  }

}
