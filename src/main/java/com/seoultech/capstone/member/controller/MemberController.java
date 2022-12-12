package com.seoultech.capstone.member.controller;

import static com.seoultech.capstone.config.jwt.JwtFilter.AUTHORIZATION_HEADER;
import static com.seoultech.capstone.config.jwt.JwtFilter.BEARER_PREFIX;

import com.seoultech.capstone.config.jwt.JwtConfig;
import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.member.service.MyPage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  private ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok()
        .body(memberService.login(request.getAccessToken()));
  }

  @GetMapping("/mypage")
  public ResponseEntity<MyPage> myPage(HttpServletRequest servletRequest) {

    String token = resolveToken(servletRequest);
    String memberId = validateToken(token);

    return ResponseEntity.ok().body(memberService.findMyPage(memberId));
  }

  //  @PatchMapping
//  public ResponseEntity<Void> editProfile() {
//    return ResponseEntity.ok().build();
//  }
//
//  @DeleteMapping
//  public ResponseEntity<Void> deleteMember() {
//    return ResponseEntity.ok().build();
//  }
  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
      return bearerToken.substring(7);
    }
    return null;
  }

  private String validateToken(String token) {

    byte[] decode = Decoders.BASE64.decode(JwtConfig.JWT_SECRET);
    Key key = Keys.hmacShaKeyFor(decode);

    try {
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
      return claims.get("jti", String.class);
    } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
      throw e;
    }
  }

}
