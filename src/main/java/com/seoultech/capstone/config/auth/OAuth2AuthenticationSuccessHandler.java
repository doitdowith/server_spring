package com.seoultech.capstone.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seoultech.capstone.TokenDto;
import com.seoultech.capstone.jwt.TokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements
    AuthenticationSuccessHandler {

  private final TokenProvider tokenProvider;
  private final ObjectMapper objectMapper;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    TokenDto tokenDto = tokenProvider.generateToken(authentication);
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");

    String result = objectMapper.writeValueAsString(tokenDto);
    response.getWriter().write(result);
  }
}
