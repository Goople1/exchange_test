package com.examplesb.demosb.web;

import static io.reactivex.schedulers.Schedulers.io;


import com.examplesb.demosb.business.login.LoginOperation;
import com.examplesb.demosb.model.request.LoginRequest;
import com.examplesb.demosb.model.response.LoginDto;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("${application.client.api.path}")
@Api(value = "LoginController",
    tags = {"Login Controller"})
@Slf4j
@RestController
public class LoginController {

  private LoginOperation loginOperation;
  @Autowired
  ResourceOwnerPasswordResourceDetails resource;

  public LoginController(LoginOperation loginOperation) {
    this.loginOperation = loginOperation;
  }

  @PostMapping(value = "/login", produces =
      MediaType.APPLICATION_STREAM_JSON_VALUE)
  @ApiOperation(
      value = "Encargado de ejecutar el logueo del cliente.",
      produces = MediaType.APPLICATION_STREAM_JSON_VALUE,
      httpMethod = "POST",
      notes = "classpath:swagger/notes/login.md")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Se obtuvo un resultado correcto.",
      response = LoginDto.class, responseHeaders = {})})
  public Single<ResponseEntity> login(@RequestBody
                                          LoginRequest loginRequest) {

    log.info("Login");

    return loginOperation.login(loginRequest)
        .subscribeOn(io());
  }

}
