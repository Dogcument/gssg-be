package com.gssg.gssgbe.controller.auth.response;

import com.gssg.gssgbe.common.token.JwtAuthToken;
import com.gssg.gssgbe.common.token.RefreshToken;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

  private String jwt;
  private String refreshToken;

  public LoginResponse(JwtAuthToken jwtAuthToken, RefreshToken refreshToken) {
    this.jwt = jwtAuthToken.getToken();
    this.refreshToken = refreshToken.getToken();
  }
}
