package com.seoultech.capstone.calendar.controller;

import static com.seoultech.capstone.config.jwt.JwtFilter.AUTHORIZATION_HEADER;
import static com.seoultech.capstone.config.jwt.JwtFilter.BEARER_PREFIX;

import com.seoultech.capstone.calendar.service.CalenderService;
import com.seoultech.capstone.config.jwt.JwtConfig;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.service.MemberService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/calendar")
public class CalendarController {

  private final CalenderService calendarService;
  private final MemberService memberService;

  @GetMapping("/my")
  public ResponseEntity<ListCalendarResponse> calendarList(HttpServletRequest servletRequest) {
    String token = resolveToken(servletRequest);
    String memberId = validateToken(token);

    Member member = memberService.findMemberById(memberId);

    return ResponseEntity.ok()
        .body(ListCalendarResponse.from(calendarService.roomList(member)));
  }

  @GetMapping("/{calendarId}")
  public ResponseEntity<Void> detailMission() {
    return ResponseEntity.ok().build();
  }

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
