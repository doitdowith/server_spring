package com.seoultech.capstone.member.controller.dto.response;

import com.seoultech.capstone.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberOAuthResponse {

  private String accessToken;
  private String refreshToken;

  public static MemberOAuthResponse from(TokenDto token) {
    return new MemberOAuthResponse(token.getAccessToken(), token.getRefreshToken());
  }

}
